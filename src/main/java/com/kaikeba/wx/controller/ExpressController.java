package com.kaikeba.wx.controller;

import com.kaikeba.bean.BootstrapTableExpress;
import com.kaikeba.bean.Express;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.User;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.ExpressService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ExpressController {
    @ResponseBody("/wx/findExpressByUserPhone.do")
    public String findByUserPhone(HttpServletRequest req, HttpServletResponse resp) {
        String json;
        Message msg = new Message();
        User wxUser = UserUtil.getWxUser(req.getSession());
        String userPhone = wxUser.getUserPhone();
        List<Express> list = ExpressService.findByUserPhone(userPhone);

        List<BootstrapTableExpress> listShow = new ArrayList<>();
        for (Express e : list) {
            String code = e.getCode() == null ? "已取件" : e.getCode();
            String inTime = DateFormatUtil.format(e.getInTime());
            String outTime = e.getOutTime() == null ? "未出库" : DateFormatUtil.format(e.getOutTime());
            String status = e.getStatus() == 0 ? "待取件" : "已取件";
            BootstrapTableExpress eShow = new BootstrapTableExpress(e.getId(), e.getNumber(), e.getUsername(), e.getUserPhone(), e.getCompany(), code, inTime, outTime, status, e.getSysPhone());
            listShow.add(eShow);
        }

        // list流： JDK1.8 处理集合的方法，流中可以使用lambda表达式进行过滤
        // lambda表达式中，对list流中所有的成员进行遍历，status = 1的被保留下来
        // list流中可以做排序
        // 排序可以用lambda表达式来操作
        if (listShow.size() == 0) {
            msg.setStatus(-1);
            msg.setResult("用户" + wxUser.getNickName() + "没有未取快递，也没有已取快递");
        } else {
            msg.setStatus(0);
            Stream<BootstrapTableExpress> status0ExpressStream = listShow.stream().filter(bootstrapTableExpress -> {
                return (bootstrapTableExpress.getStatus().equals("待取件"));
            }).sorted((o1, o2) -> {
                //// lambda表达式做排序，getInTime获取时间戳，getTime获取转换为long类型的数值
                //// 相减得到一个数，强转为int类型，正数则为大，负数则为小
                //long o1Time = o1.getInTime().getTime();
                //long o2Time = o2.getInTime().getTime();
                //return (int) (o1Time - o2Time);
                long o1Time = DateFormatUtil.toTime(o1.getInTime());
                long o2Time = DateFormatUtil.toTime(o2.getInTime());
                return (int) (o1Time - o2Time);
            });
            Stream<BootstrapTableExpress> status1ExpressStream = listShow.stream().filter(bootstrapTableExpress -> {
                return (bootstrapTableExpress.getStatus().equals("已取件"));
            }).sorted((o1, o2) -> {
                //long o1Time = o1.getInTime().getTime();
                //long o2Time = o2.getInTime().getTime();
                long o1Time = DateFormatUtil.toTime(o1.getInTime());
                long o2Time = DateFormatUtil.toTime(o2.getInTime());
                return (int) (o1Time - o2Time);
            });

            Object[] s0 = status0ExpressStream.toArray();
            Object[] s1 = status1ExpressStream.toArray();
            Map dataExpress = new HashMap<>();
            dataExpress.put("status0", s0);
            dataExpress.put("status1", s1);
            msg.setData(dataExpress);
            msg.setResult("查询用户" + wxUser.getNickName() + "快递成功");
        }

        json = JSONUtil.toJSON(msg);
        return json;
    }
}
