package com.kaikeba.service;

import com.kaikeba.bean.Courier;
import com.kaikeba.dao.BaseCourierDao;
import com.kaikeba.dao.imp.CourierDaoMysql;

import java.util.List;

public class CourierService {

    BaseCourierDao dao = new CourierDaoMysql();

    /**
     * 查询数据库，搜索手机号，得到快递员对象
     *
     * @param userPhone 快递员的手机号
     * @return 快递员对象, null为查询无此人
     */
    public Courier findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

    /**
     * 插入数据库，新增快递员信息
     *
     * @param courier 新增快递员对象
     * @return true=成功, false=失败
     */
    public Boolean insert(Courier courier) {
        return dao.insert(courier);
    }

    /**
     * 更新数据库，修改快递员信息
     *
     * @param oldCourier 查找的快递员信息（手机号码是唯一匹配）
     * @param newCourier 快递员信息
     * @return true=成功, false=失败
     */
    public Boolean update(Courier oldCourier, Courier newCourier) {
        return dao.update(oldCourier, newCourier);
    }

    /**
     * 从数据库删除，快递员信息
     *
     * @param courier 要删除的快递员信息
     * @return true=成功，false=失败
     */
    public Boolean delete(Courier courier) {
        return dao.delete(courier);
    }

    /**
     * 查询数据库，获取快递员列表
     *
     * @param limit      是否分页
     * @param offset     第offset个数据
     * @param pageNumber 每页展示的数据
     * @return 快递员列表
     */
    public List<Courier> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }


    /**
     * 查询数据库，获取快递员的数量
     * @param toDate        <0为所有快递员，toDate=几天内新增快递员
     * @return              返回快递员数量
     */
    public Integer count(int toDate) {
        return dao.count(toDate);
    }
}
