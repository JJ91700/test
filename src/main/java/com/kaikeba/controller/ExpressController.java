package com.kaikeba.controller;

import com.kaikeba.bean.BootstrapTableExpress;
import com.kaikeba.bean.Express;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.ResultData;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.ExpressService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpressController {
    @ResponseBody("/express/console.do")
    public String console(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Integer>> data = ExpressService.console();
        Message msg = new Message();
        if (data.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
        }
        msg.setData(data);

        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/express/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        // 需要前端与后端协商好，传递的数据，如果不传会出异常
        // 1. 获取查询数据的起始索引值
        //String offset = request.getParameter("offset");
        int offset = Integer.parseInt(request.getParameter("offset"));
        // 2. 获取当前页要查询的数据量
        //String pageNumber = request.getParameter("pageNumber");
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

        // 3. 进行分页查询
        List<Express> list = ExpressService.findAll(true, offset, pageNumber);
        List<BootstrapTableExpress> listShow = new ArrayList<>();
        for (Express e : list) {
            String code = e.getCode() == null ? "已取件" : e.getCode();
            String inTime = DateFormatUtil.format(e.getInTime());
            String outTime = e.getOutTime() == null ? "未出库" : DateFormatUtil.format(e.getOutTime());
            String status = e.getStatus() == 0 ? "待取件" : "已取件";
            BootstrapTableExpress eShow = new BootstrapTableExpress(e.getId(), e.getNumber(), e.getUsername(), e.getUserPhone(), e.getCompany(), code, inTime, outTime, status, e.getSysPhone());
            listShow.add(eShow);
        }

        // 4. 将集合封装为 bootstrap-table识别的格式
        //ResultData<Express> data = new ResultData<>();
        ResultData<BootstrapTableExpress> data = new ResultData<>();
        List<Map<String, Integer>> console = ExpressService.console();
        Integer total = console.get(0).get("data1_size");
        data.setRows(listShow);
        data.setTotal(total);

        String json = JSONUtil.toJSON(data);
        return json;
    }

    @ResponseBody("/express/insert.do")
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");

        String sysPhone = UserUtil.getUserPhone(request.getSession());
        Express e = new Express(number, username, userPhone, company, sysPhone);
        boolean flag = ExpressService.insert(e);
        Message msg = new Message();
        if (flag) {
            // 录入成功
            msg.setStatus(0);
            msg.setResult("快递录入成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("快递录入失败");
        }

        String json = JSONUtil.toJSON(msg);
        return json;
    }
}
