package com.kaikeba.dao.imp;

import com.kaikeba.bean.Courier;
import com.kaikeba.dao.BaseCourierDao;
import com.kaikeba.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierDaoMysql implements BaseCourierDao {

    private static final String SQL_FIND_BY_USERPHONE = "SELECT * FROM eadmin WHERE USERPHONE=?";
    // INSERT INTO eadmin
    //         (id, username, userphone, cardid, password, sendexpress, createtime, logintime, loginip, admin)
    // VALUES(NULL, "赵六", "13666666666", "447123456789", "876543", 0, NOW(), NOW(), "127.0.0.1", 0);
    private static final String SQL_INSERT_COURIER = "INSERT INTO eadmin (id, username, userphone, cardid, `password`, sendexpress, createtime, logintime, loginip, admin) VALUES(NULL, ?, ?, ?, ?, 0, NOW(), NOW(), ?, 0)";

    private static final String SQL_UPDATE = "UPDATE eadmin SET username=?, userphone=?, cardid=?, `password`=? WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM eadmin WHERE id=?";
    private static final String SQL_FIND_ALL_WITHOUT_LIMIT = "SELECT * FROM eadmin";
    private static final String SQL_FIND_ALL_WITH_LIMIT = "SELECT * FROM eadmin LIMIT ?, ?";
    private static final String SQL_SET_ADMIN = "UPDATE eadmin SET `admin`=? WHERE `userPhone`=?";
    private static final String SQL_REMOVE_ADMIN = "UPDATE eadmin SET `admin`=? WHERE `userPhone`=?";
    // =0为当前一天，>0为昨天及其前面所有的记录
    private static final String SQL_COUNT_ALL = "SELECT COUNT(*) count_courier FROM eadmin WHERE TO_DAYS(NOW())-TO_DAYS(createtime)>=0";
    // <=0为当前一天，<=1为昨天和今天，<=2为前天，昨天和今天
    private static final String SQL_COUNT_TO_DAYS = "SELECT COUNT(*) count_courier FROM eadmin WHERE TO_DAYS(NOW())-TO_DAYS(createtime)<=?";

    /**
     * 查询数据库，搜索手机号，得到快递员对象
     *
     * @param userPhone 快递员的手机号
     * @return 快递员对象, null为查询无此人
     */
    @Override
    public Courier findByUserPhone(String userPhone) {
        Courier courier = null;
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
                courier = new Courier(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("userPhone"),
                        rs.getString("cardId"),
                        rs.getString("password"),
                        rs.getInt("sendExpress"),
                        rs.getTimestamp("createTime"),
                        rs.getTimestamp("loginTime"),
                        rs.getString("loginIp"),
                        rs.getInt("admin"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, rs);
        }
        // 6. 释放资源
        return courier;
    }

    /**
     * 插入数据库，新增快递员信息
     *
     * @param courier 新增快递员对象
     * @return true=成功, false=失败
     */
    @Override
    public Boolean insert(Courier courier) {
        boolean result = false;
        // 1. 获取数据库连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_INSERT_COURIER);

            // 3. 填充参数
            // INSERT INTO eadmin
            //         (id, username, userphone, cardid, password, sendexpress, createtime, logintime, loginip, admin)
            // VALUES(NULL, "赵六", "13666666666", "447123456789", "876543", 0, NOW(), NOW(), "127.0.0.1", 0);
            state.setString(1, courier.getUserName());
            state.setString(2, courier.getUserPhone());
            state.setString(3, courier.getCardId());
            state.setString(4, courier.getPassword());
            state.setString(5, courier.getLoginIp());

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
     * 更新数据库，修改快递员信息
     *
     * @param oldCourier 查找的快递员信息（手机号是唯一匹配）
     * @param newCourier 快递员信息
     * @return true=成功, false=失败
     */
    @Override
    public Boolean update(Courier oldCourier, Courier newCourier) {
        boolean result = false;
        // 1. 获取数据库连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE);
            // 3. 填充参数
            //UPDATE eadmin SET username=?, userphone=?, cardid=?, `password`=? WHERE userphone=?;
            state.setString(1, newCourier.getUserName());
            state.setString(2, newCourier.getUserPhone());
            state.setString(3, newCourier.getCardId());
            state.setString(4, newCourier.getPassword());

            //state.setString(5, oldCourier.getUserPhone());
            state.setInt(5, newCourier.getId());

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
     * 从数据库删除，快递员信息
     *
     * @param courier 要删除的快递员信息
     * @return true=成功，false=失败
     */
    @Override
    public Boolean delete(Courier courier) {
        boolean result = false;
        // 1. 获取数据库连接
        Connection conn = DruidUtil.getConnection();
        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_DELETE);
            // 3. 填充参数
            state.setInt(1, courier.getId());

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
     * 查询数据库，获取快递员列表
     *
     * @param limit      是否分页
     * @param offset     第offset个数据
     * @param pageNumber 每页展示的数据
     * @return 快递员列表
     */
    @Override
    public List<Courier> findAll(boolean limit, int offset, int pageNumber) {
        List<Courier> list = new ArrayList();
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
                Courier courier = new Courier(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("userPhone"),
                        rs.getString("cardId"),
                        rs.getString("password"),
                        rs.getInt("sendExpress"),
                        rs.getTimestamp("createTime"),
                        rs.getTimestamp("loginTime"),
                        rs.getString("loginIp"),
                        rs.getInt("admin"));
                list.add(courier);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, rs);
        }
        return list;
    }

    /**
     * 查询数据库，获取快递员的数量
     *
     * @param toDate <0为所有快递员，toDate=几天内新增快递员
     * @return 返回快递员数量
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
                count = rs.getInt("count_courier");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, rs);
        }
        return count;
    }

    /**
     * 更新数据库，设置快递员为管理员（dao是原子操作，权限需要在service层做限制，只有管理员才能设置其他快递员为管理员）
     *
     * @param userPhone 设置手机号为userPhone的快递员为管理员
     * @return true=成功，false=失败
     */
    @Override
    public Boolean addAdmin(String userPhone) {
        boolean result = false;
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_SET_ADMIN);
            state.setInt(1, 1);
            state.setString(2, userPhone);
            result = state.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, rs);
        }
        return result;
    }

    /**
     * 更新数据库，移除管理员（dao是原子操作，权限需要在service层做限制，只有管理员才能设置其他快递员为管理员）
     *
     * @param userPhone 设置手机号为userPhone的管理员为普通快递员
     * @return true=成功, false=失败
     */
    @Override
    public Boolean removeAdmin(String userPhone) {
        boolean result = false;
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_REMOVE_ADMIN);
            state.setInt(1, 0);
            state.setString(2, userPhone);
            result = state.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, rs);
        }
        return result;
    }
}
