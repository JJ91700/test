package com.kaikeba.filter;

import com.kaikeba.util.UserUtil;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "AccessControlFilter")
@WebFilter({"/admin/index.html", "/admin/views/*", "/express/*"})
public class AccessControlFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String userName = UserUtil.getUserName(req.getSession());
        if (userName != null) {
            chain.doFilter(request, response);
        } else {
            resp.sendError(404, "很遗憾，权限不足");
        }
    }
}
