package com.kaikeba.dao.imp;

import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminDaoMysql implements BaseAdminDao {
    // 可以直接传入当前时间
    // private static final String SQL_UPDATE_LOGIN_TIME = "UPDATE EADMIN SET LOGINTIME=NOW(), LOGINGIP=? WHERE USERNAME=?";
    private static final String SQL_UPDATE_LOGIN_TIME = "UPDATE EADMIN SET LOGINTIME=?, LOGINGIP=? WHERE USERNAME=?";
    private static final String SQL_LOGIN = "SELECT ID FROM EADMIN WHERE USERNAME=? AND PASSWORD=?";

    /**
     * 根据用户名，更新登录时间和登录ip
     *
     * @param username 用户名
     * @param date     日期时间
     * @param ip       ip地址
     */
    @Override
    public void updateLoginTime(String username, Date date, String ip) {
        // 1. 获取连接
        Connection conn = DruidUtil.getConnection();
        // 2. 预编译SQL语句
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE_LOGIN_TIME);
            // 3. 填充参数
            state.setDate(1, new java.sql.Date(date.getTime()));
            state.setString(2, ip);
            state.setString(3, username);
            // 4. 执行
            state.executeUpdate();
            // 如果需要日志，等操作，这里需要返回值，暂时不考虑
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 5. 释放资源
            DruidUtil.close(conn, state, null);
        }

    }

    /**
     * 管理员根据账号密码登录
     *
     * @param username 账号
     * @param password 密码
     * @return 登录结果，true：成功， false：失败
     */
    @Override
    public boolean login(String username, String password) {
        // 1. 获取连接
        Connection conn = DruidUtil.getConnection();
        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_LOGIN);
            // 3. 填充参数
            state.setString(1, username);
            state.setString(2, password);
            // 4. 执行
            rs = state.executeQuery();
            // 5. 根据查询结果，返回true成功/false失败
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 5. 释放资源
            DruidUtil.close(conn, state, rs);
        }

        // 异常返回false
        return false;
    }
}
