package com.kaikeba.service;

import com.kaikeba.bean.User;
import com.kaikeba.dao.BaseUserDao;
import com.kaikeba.dao.imp.UserDaoMysql;

import java.util.List;

public class UserService {
    private BaseUserDao dao = new UserDaoMysql();
    /**
     * 查询数据库，搜索手机号，得到用户对象
     *
     * @param userPhone 用户的手机号
     * @return 用户对象, null为查询无此人
     */
    public User findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

    /**
     * 插入数据库，新增用户信息
     *
     * @param user 新增用户对象
     * @return true=成功, false=失败
     */
    public Boolean insert(User user) {
        return dao.insert(user);
    }

    /**
     * 更新数据库，修改用户信息
     *
     * @param newUser 用户信息，查询成功后重新封装id等信息直接update
     * @return true=成功, false=失败
     */
    public Boolean update(User newUser) {
        return dao.update(newUser);
    }

    /**
     * 从数据库删除，用户信息
     *
     * @param user 要删除的用户信息
     * @return true=成功，false=失败
     */
    public Boolean delete(User user) {
        return dao.delete(user);
    }

    /**
     * 查询数据库，获取用户列表
     *
     * @param limit      是否分页
     * @param offset     第offset个数据
     * @param pageNumber 每页展示的数据
     * @return 用户列表
     */
    public List<User> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    /**
     * 查询数据库，获取用户的数量
     *
     * @param toDate <0为所有用户，toDate=几天内新增用户
     * @return 返回用户数量
     */
    public Integer count(int toDate) {
        return dao.count(toDate);
    }
}
