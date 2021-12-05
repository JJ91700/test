package com.kaikeba.dao.imp;

import com.kaikeba.bean.User;
import com.kaikeba.dao.BaseUserDao;
import com.kaikeba.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMysql implements BaseUserDao {

    private static final String SQL_FIND_BY_USERPHONE = "SELECT * FROM euser WHERE USERPHONE=?";
    private static final String SQL_INSERT = "INSERT INTO euser (id, nickname, userphone, cardid, `password`, createtime, logintime) VALUES(NULL, ?, ?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE = "UPDATE euser SET nickname=?, userphone=?, cardid=?, `password`=? WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM euser WHERE id=?";
    private static final String SQL_FIND_ALL_WITHOUT_LIMIT = "SELECT * FROM euser";
    private static final String SQL_FIND_ALL_WITH_LIMIT = "SELECT * FROM euser LIMIT ?, ?";
    // =0为当前一天，>0为昨天及其前面所有的记录
    private static final String SQL_COUNT_ALL = "SELECT COUNT(*) count_user FROM euser WHERE TO_DAYS(NOW())-TO_DAYS(createtime)>=0";
    // <=0为当前一天，<=1为昨天和今天，<=2为前天，昨天和今天
    private static final String SQL_COUNT_TO_DAYS = "SELECT COUNT(*) count_user FROM euser WHERE TO_DAYS(NOW())-TO_DAYS(createtime)<=?";

    /**
     * 查询数据库，搜索手机号，得到用户对象
     *
     * @param userPhone 用户的手机号
     * @return 用户对象, null为查询无此人
     */
    @Override
    public User findByUserPhone(String userPhone) {
        User user = null;
        // 1. 获取数据库连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_USERPHONE);

            // 3. 填充参数
            state.setString(1, userPhone);

            // 4. 执行
            rs = state.executeQuery();

            // 5. 获取结果
            while (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("nickName"),
                        rs.getString("userPhone"),
                        rs.getString("cardId"),
                        rs.getString("password"),
                        rs.getTimestamp("createTime"),
                        rs.getTimestamp("loginTime"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, rs);
        }
        // 6. 释放资源
        return user;
    }

    /**
     * 插入数据库，新增用户信息
     *
     * @param user 新增用户对象
     * @return true=成功, false=失败
     */
    @Override
    public Boolean insert(User user) {
        boolean result = false;
        // 1. 获取数据库连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_INSERT);

            // 3. 填充参数
            //INSERT INTO euser (id, nickname, userphone, cardid, `password`, createtime, logintime)
            //VALUES(NULL, ?, ?, ?, ?, NOW(), NOW())
            state.setString(1, user.getNickName());
            state.setString(2, user.getUserPhone());
            state.setString(3, user.getCardId());
            state.setString(4, user.getPassword());

            // 4. 执行
            // 5. 获取结果
            result = state.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6. 释放资源
            DruidUtil.close(conn, state, rs);
        }
        return result;
    }

    /**
     * 更新数据库，修改用户信息
     *
     * @param newUser 用户信息，查询成功后重新封装id等信息直接update
     * @return true=成功, false=失败
     */
    @Override
    public Boolean update(User newUser) {
        boolean result = false;
        // 1. 获取数据库连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE);
            // 3. 填充参数
            //UPDATE euser SET nickname=?, userphone=?, cardid=?, `password`=? WHERE id=?
            state.setString(1, newUser.getNickName());
            state.setString(2, newUser.getUserPhone());
            state.setString(3, newUser.getCardId());
            state.setString(4, newUser.getPassword());

            //state.setString(5, oldUser.getUserPhone());
            state.setInt(5, newUser.getId());

            // 4. 执行
            // 5. 获取结果
            result = state.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6. 释放资源
            DruidUtil.close(conn, state, rs);
        }

        return result;
    }

    /**
     * 从数据库删除，用户信息
     *
     * @param user 要删除的用户信息
     * @return true=成功，false=失败
     */
    @Override
    public Boolean delete(User user) {
        boolean result = false;
        // 1. 获取数据库连接
        Connection conn = DruidUtil.getConnection();
        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_DELETE);
            // 3. 填充参数
            state.setInt(1, user.getId());

            // 4. 执行
            // 5. 获取结果
            result = state.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6. 释放资源
            DruidUtil.close(conn, state, rs);
        }
        return result;
    }

    /**
     * 查询数据库，获取用户列表
     *
     * @param limit      是否分页
     * @param offset     第offset个数据
     * @param pageNumber 每页展示的数据
     * @return 用户列表
     */
    @Override
    public List<User> findAll(boolean limit, int offset, int pageNumber) {
        List<User> list = new ArrayList();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            if (limit) {
                state = conn.prepareStatement(SQL_FIND_ALL_WITH_LIMIT);
                state.setInt(1, offset);
                state.setInt(2, pageNumber);
            } else {
                state = conn.prepareStatement(SQL_FIND_ALL_WITHOUT_LIMIT);
            }

            rs = state.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("nickName"),
                        rs.getString("userPhone"),
                        rs.getString("cardId"),
                        rs.getString("password"),
                        rs.getTimestamp("createTime"),
                        rs.getTimestamp("loginTime"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, rs);
        }
        return list;
    }

    /**
     * 查询数据库，获取用户的数量
     *
     * @param toDate <0为所有用户，toDate=几天内新增用户
     * @return 返回用户数量
     */
    @Override
    public Integer count(int toDate) {
        int count = 0;
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            if (toDate < 0) {
                state = conn.prepareStatement(SQL_COUNT_ALL);
            } else {
                state = conn.prepareStatement(SQL_COUNT_TO_DAYS);
                state.setInt(1, toDate);
            }
            rs = state.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count_user");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, rs);
        }
        return count;
    }
}
