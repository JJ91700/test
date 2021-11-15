package com.kaikeba.dao;

import java.util.Date;

/**
 * 用于定义eadmin表格的操作规范
 */
public interface BaseAdminDao {
    /**
     * 根据用户名，更新登录时间和登录ip
     * @param username 用户名
     * @param date     日期时间
     * @param ip       ip地址
     */
    void updateLoginTime(String username, Date date, String ip);

    /**
     * 管理员根据账号密码登录
     * @param username  账号
     * @param password  密码
     * @return          登录结果，true：成功， false：失败
     */
    boolean login(String username, String password);
}
