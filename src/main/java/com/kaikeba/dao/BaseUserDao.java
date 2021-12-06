package com.kaikeba.dao;

import com.kaikeba.bean.User;

import java.util.List;

public interface BaseUserDao {
    /**
     * 查询数据库，搜索手机号，得到快递员对象
     * @param userPhone     快递员的手机号
     * @return              快递员对象, null为查询无此人
     */
    User findByUserPhone(String userPhone);

    /**
     * 插入数据库，新增快递员信息
     * @param user      新增快递员对象
     * @return          true=成功, false=失败
     */
    Boolean insert(User user);

    /**
     * 更新数据库，修改快递员信息
     * @param newUser       快递员信息，查询成功后重新封装id等信息直接update
     * @return              true=成功, false=失败
     */
    Boolean update(User newUser);

    /**
     * 从数据库删除，快递员信息
     * @param user          要删除的快递员信息
     * @return              true=成功，false=失败
     */
    Boolean delete(User user);

    /**
     * 查询数据库，获取快递员列表
     * @param limit         是否分页
     * @param offset        第offset个数据
     * @param pageNumber    每页展示的数据
     * @return              快递员列表
     */
    List<User> findAll(boolean limit, int offset, int pageNumber);

    /**
     * 查询数据库，获取快递员的数量
     * @param toDate        <0为所有快递员，toDate=几天内新增快递员
     * @return              返回快递员数量
     */
    Integer count(int toDate);

    /**
     * 查询数据库，设置user的isCourier属性
     * @param userPhone     根据userPhone查询数据库
     * @param isCourier     设置为是否快递员
     * @return              数据库操作结果，0=失败，1=成功
     */
    Boolean setCourier(String userPhone, boolean isCourier);
}
