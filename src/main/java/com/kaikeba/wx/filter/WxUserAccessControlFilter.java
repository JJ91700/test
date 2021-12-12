package com.kaikeba.wx.filter;

import com.kaikeba.bean.Courier;
import com.kaikeba.bean.User;
import com.kaikeba.util.UserUtil;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "WxUserAccessControlFilter")
//@WebFilter("/index.html")
@WebFilter({"/index.html", "/expressList.html"})
public class WxUserAccessControlFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        User user = UserUtil.getWxUser(req.getSession());
        Courier courier = UserUtil.getWxCourier(req.getSession());
        if (user != null || courier != null) {
            chain.doFilter(request, response);
        } else {
            req.getSession().invalidate();
            resp.sendRedirect("login.html");
        }
    }
}
