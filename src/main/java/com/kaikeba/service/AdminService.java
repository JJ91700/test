package com.kaikeba.service;

import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.dao.imp.AdminDaoMysql;

import java.util.Date;

public class AdminService {

    private static BaseAdminDao dao = new AdminDaoMysql();

    /**
     * 根据用户名，更新登录时间和登录ip
     * @param username 用户名
     * @param date     日期时间
     * @param ip       ip地址
     */
    public static void updateLoginTimeAndIp(String username, Date date, String ip) {
        dao.updateLoginTime(username, date, ip);
    }

    /**
     * 管理员根据账号密码登录
     * @param username  账号
     * @param password  密码
     * @return          登录结果，true：成功， false：失败
     */
    public static boolean login(String username, String password) {
        return dao.login(username, password);
    }
}
