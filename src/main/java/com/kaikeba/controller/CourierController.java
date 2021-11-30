package com.kaikeba.controller;

import com.kaikeba.bean.BootstrapTableCourier;
import com.kaikeba.bean.Courier;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.ResultData;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.CourierService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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

    @ResponseBody("/courier/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) {
        String json = null;
        int offset = Integer.parseInt(req.getParameter("offset"));
        System.out.println("offset = " + offset);
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        System.out.println("pageNumber = " + pageNumber);

        CourierService service = new CourierService();
        List<Courier> list = service.findAll(true, offset, pageNumber);
        List<BootstrapTableCourier> listShow = new ArrayList();
        for (Courier c : list) {
            String sendExpress = c.getSendExpress().toString();
            String createTime = DateFormatUtil.format(c.getCreateTime());
            String loginTime = DateFormatUtil.format(c.getLoginTime());
            String admin = c.getAdmin() == 1 ? "管理员" : "快递员";

            BootstrapTableCourier showCourier = new BootstrapTableCourier(c.getId(), c.getUserName(), c.getUserPhone(),
                    c.getCardId(), c.getPassword(), sendExpress, createTime, loginTime, c.getLoginIp(), admin);
            listShow.add(showCourier);
        }

        ResultData<BootstrapTableCourier> data = new ResultData<>();
        int total = service.count(-1);
        data.setRows(listShow);
        data.setTotal(total);

        json = JSONUtil.toJSON(data);
        return json;
    }
}
