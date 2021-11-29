package com.kaikeba.controller;

import com.kaikeba.bean.Courier;
import com.kaikeba.bean.Message;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.CourierService;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourierController {

    @ResponseBody("/courier/insert.do")
    public String insert(HttpServletRequest req, HttpServletResponse resp) {
//        // 测试ip地址，环境：Win10，Chrome版本 96.0.4664.45（正式版本） （64 位），Tomcat 8.5.34
//        // 1. http://localhost:8080  登录使用ipv6的地址：0:0:0:0:0:0:0:1
//        // 2. 127.0.0.1:8080    使用ipv4地址 127.0.0.1
//        // 3. 172.17.30.152:8080     使用ipv4地址 172.17.30.152
//        String ip = req.getRemoteAddr();
//        Message message = new Message();
//        message.setResult(ip);
//        return JSONUtil.toJSON(message);


        // 1. 获取req参数
        String userName = req.getParameter("userName");
        String userPhone = req.getParameter("userPhone");
        String cardId = req.getParameter("cardId");
        String password = req.getParameter("password");

        // 2. 处理数据
        Courier courier = new Courier(userName, userPhone, cardId, password);
        String ip = req.getRemoteAddr();
        courier.setLoginIp(ip);

        // 3. 操作数据库
        CourierService courierService = new CourierService();
        Boolean insert = courierService.insert(courier);

        // 4. 返回结果
        Message msg = new Message();
        String json = null;
        if (insert) {
            msg.setStatus(0);
            msg.setResult("快递员信息录入成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("快递员信息录入失败");
        }

        json = JSONUtil.toJSON(msg);
        return json;
    }
}
