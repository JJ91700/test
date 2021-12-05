package com.kaikeba.controller;

import com.kaikeba.bean.Message;
import com.kaikeba.bean.User;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.UserService;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController {
    private UserService service = new UserService();
    @ResponseBody("/user/insert.do")
    public String insert(HttpServletRequest req, HttpServletResponse resp) {
        String json;
        String nickName = req.getParameter("nickName");
        String userPhone = req.getParameter("userPhone");
        String cardId = req.getParameter("cardId");
        String password = req.getParameter("password");

        User user = new User(nickName, userPhone, cardId, password);
        Boolean insert = service.insert(user);
        Message msg = new Message();
        if (insert) {
            // 新增用户成功
            msg.setStatus(0);
            msg.setResult("新增用户 '" + nickName + "' 成功");
        } else {
            // 新增用户失败
            msg.setStatus(-1);
            msg.setResult("新增用户 '" + nickName + "' 失败");
        }

        json = JSONUtil.toJSON(msg);
        return json;
    }
}
