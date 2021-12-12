package com.kaikeba.wx.controller;

import com.kaikeba.bean.Message;
import com.kaikeba.bean.User;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.mvc.ResponseView;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QRCodeController {

    // 使用 ResponseView 是直接跳转到返回的字符串地址
    @ResponseView("/wx/createQRCode.do")
    public String createQrcode(HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("code");
        // 2种类型的QRCode，user页面 | express页面
        String type = req.getParameter("type");
        //System.out.println("type = " + type);

        String userPhone = null;
        String QRCodeContent = null;
        HttpSession session = req.getSession();
        if ("express".equals(type)) {
            // 快递二维码：被扫后，展示单个快递的信息
            // 要传的参数 code
            QRCodeContent = "express_" + code;
        } else if ("user".equals(type)) {
            // 用户二维码：被扫后，快递员（柜子）端展示用户所有快递
            // 要传的参数 userPhone
            User wxUser = UserUtil.getWxUser(session);
            userPhone = wxUser.getUserPhone();
            QRCodeContent = "userPhone_" + userPhone;
        } else {
            // 暂未处理的情况
        }

        session.setAttribute("QRCodeContent", QRCodeContent);
        //System.out.println("QRCodeContent = " + QRCodeContent);
        return "/personQRcode.html";
    }

    @ResponseBody("/wx/qrcode.do")
    public String getQRCode(HttpServletRequest req, HttpServletResponse resp) {
        String json;
        HttpSession session = req.getSession();
        String qrCodeContent = (String) session.getAttribute("QRCodeContent");
        Message msg = new Message();
        if (qrCodeContent == null) {
            msg.setStatus(-1);
            msg.setResult("取件码获取出错，请用户重新操作");
        } else {
            msg.setStatus(0);
            msg.setResult(qrCodeContent);
        }
        json = JSONUtil.toJSON(msg);
        return json;
    }
}
