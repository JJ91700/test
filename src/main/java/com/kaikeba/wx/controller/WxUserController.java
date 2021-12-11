package com.kaikeba.wx.controller;

import com.alibaba.druid.sql.dialect.sqlserver.visitor.MSSQLServerExportParameterVisitor;
import com.kaikeba.bean.Courier;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.User;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.CourierService;
import com.kaikeba.service.UserService;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.RandomUtil;
import com.kaikeba.util.SMSUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WxUserController {

    @ResponseBody("/wx/loginSms.do")
    public String sendSms(HttpServletRequest req, HttpServletResponse resp) {
        String json;
        String userPhone = req.getParameter("userPhone");
        String code = RandomUtil.getCode() + "";
        boolean login = SMSUtil.login(userPhone, code);
        Message msg = new Message();
        if (login) {
            msg.setStatus(0);
            msg.setResult("登录验证码已发送，请查收");
        } else {
            msg.setStatus(1);
            msg.setResult("登录验证码发送失败，请检查手机号或者稍后再试");
        }

        UserUtil.setLoginSms(req.getSession(), userPhone, code);

        json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/login.do")
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        String json;
        String userPhone = req.getParameter("userPhone");
        String userCode = req.getParameter("code");
        String loginSms = UserUtil.getLoginSms(req.getSession(), userPhone);
        CourierService courierService = new CourierService();
        UserService userService = new UserService();

        Message msg = new Message();
        if (loginSms == null) {
            // 这个手机号未获取短信，登录失败
            msg.setStatus(-1);
            msg.setResult("手机号码未获取短信");
        } else if (loginSms.equals(userCode)) {     // 此时loginSms不为null
            // 手机号码的验证码和短信一致，登录成功
            // FIXED: 还要区分快递员和管理员
            // FIXED: 这个判断应替换为快递员表格查询手机号的结果
            User user = userService.findByUserPhone(userPhone);
            if (user != null) {                     // 注册为user
                // 已经注册过了
                if (user.isCourier()) {             // 注册为user，同时注册为courier
                    msg.setStatus(1);               // 快递员
                    msg.setResult("该手机号同时注册为快递员，是否登录为快递员");
                } else {
                    msg.setStatus(0);               // 单注册为普通用户
                    msg.setResult("用户'" + user.getNickName() + "'登陆");
                }
            } else {                                // 没有注册为user
                Courier courier = courierService.findByUserPhone(userPhone);
                if (courier != null) {
                    msg.setStatus(1);               // 单注册为快递员
                    msg.setResult("快递员'" + courier.getUserName() + "'登陆");
                    loginAsCourier(req, resp);
                } else {
                    // 既不是user，也不是courier，可能是黑客获取了手机号了验证码，建议报警
                    msg.setStatus(-3);
                    msg.setResult("如果是正常注册用户/快递员的话，不应该进这里，你是不是走了后门");
                }
            }

            // user可能为null
            UserUtil.setWxUser(req.getSession(), user);
        } else {
            // 验证码不一致，登录失败
            msg.setStatus(-2);
            msg.setResult("验证码不一致，请检查");
        }

        // FIXED: 用户/快递员登录成功，需要保存登录信息
        // TODO: 注册成功，需要保存注册信息
        json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/loginAsCourier.do")
    public String loginAsCourier(HttpServletRequest req, HttpServletResponse resp) {
        String json;
        String userPhone = req.getParameter("userPhone");
        String userCode = req.getParameter("code");

        CourierService courierService = new CourierService();
        Courier courier = courierService.findByUserPhone(userPhone);
        Message msg = new Message();
        if (courier != null) {
            UserUtil.setWxCourier(req.getSession(), courier);
            msg.setStatus(0);
            msg.setResult("登录为快递员");
        }

        json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/userInfo.do")
    public String userInfo(HttpServletRequest req, HttpServletResponse resp) {
        String json;
        Message msg = new Message();
        User user = UserUtil.getWxUser(req.getSession());
        if (user != null) {
            boolean isCourier = user.isCourier();
            if (isCourier) {
                msg.setStatus(1);
            } else {
                msg.setStatus(0);
            }
            msg.setResult(user.getUserPhone());
        } else {
            msg.setStatus(-1);
            msg.setResult("数据错误，请通知管理员检查");
        }

        json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/courierInfo.do")
    public String courierInfo(HttpServletRequest req, HttpServletResponse resp) {
        String json;
        Message msg = new Message();
        Courier courier = UserUtil.getWxCourier(req.getSession());
        if (courier != null) {
            msg.setResult(courier.getUserPhone());
            msg.setStatus(1);
        } else {
            User user = UserUtil.getWxUser(req.getSession());
            msg.setResult(user.getUserPhone());
            msg.setStatus(0);
        }

        json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/logout.do")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 销毁session
        req.getSession().invalidate();
        // 2. 给客户端回复消息
        Message msg = new Message(0);
        // 3. 返回消息
        return JSONUtil.toJSON(msg);
    }
}
