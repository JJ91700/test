package com.kaikeba.controller;

import com.kaikeba.bean.BootstrapTableUser;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.ResultData;
import com.kaikeba.bean.User;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.UserService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @ResponseBody("/user/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) {
        String json;
        int offset = Integer.parseInt(req.getParameter("offset"));
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));

        List<User> originalUsers = service.findAll(true, offset, pageNumber);
        List<BootstrapTableUser> showUsers = new ArrayList();
        for (User user : originalUsers) {
            Timestamp ts = user.getCreateTime();
            System.out.println("ts = " + ts);
            String createTime = DateFormatUtil.format(ts);
            System.out.println("createTime = " + createTime);

            String loginTime = DateFormatUtil.format(user.getLoginTime());
            BootstrapTableUser bootstrapTableUser = new BootstrapTableUser(user.getId(), user.getNickName(),
                    user.getUserPhone(), user.getCardId(), user.getPassword(), createTime, loginTime);
            showUsers.add(bootstrapTableUser);
        }

        int total =  service.count(-1);
        ResultData<BootstrapTableUser> list = new ResultData<>();
        list.setRows(showUsers);
        list.setTotal(total);

        json = JSONUtil.toJSON(list);
        return json;
    }
}
