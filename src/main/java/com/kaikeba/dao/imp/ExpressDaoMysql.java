package com.kaikeba.dao.imp;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.exception.DuplicateCodeException;
import com.kaikeba.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressDaoMysql implements BaseExpressDao {

    public static final String SQL_CONSOLE = "SELECT "
            +"COUNT(ID) data1_size, "
            +"COUNT(TO_DAYS(INTIME)=TO_DAYS(NOW()) OR NULL) data1_day, "
            +"COUNT(STATUS=0 OR NULL) data2_size, "
            +"COUNT(TO_DAYS(INTIME)=TO_DAYS(NOW()) AND STATUS=0 OR NULL) data2_day "
            +" FROM express";
    // 用于查询数据库中的所有快递信息
    public static final String SQL_FIND_ALL = "SELECT * FROM express";
    // 用于分页查询数据库中的快递信息
    public static final String SQL_FIND_LIMIT = "SELECT * FROM express LIMIT ?,?";
    // 通过取件码查询快递信息
    public static final String SQL_FIND_BY_CODE = "SELECT * FROM express WHERE code=?";
    // 通过快递单号查询快递信息
    public static final String SQL_FIND_BY_NUMBER = "SELECT * FROM express WHERE number=?";
    // 通过录入人的手机号码查询快递信息
    public static final String SQL_FIND_BY_SYSPHONE = "SELECT * FROM express WHERE sysphone=?";
    // 通过用户的手机号码查询快递信息
    public static final String SQL_FIND_BY_USERPHONE = "SELECT * FROM express WHERE userphone=?";
    // 快递录入
    public static final String SQL_INSERT = "INSERT INTO express "
            +"(number, username, userphone, company, code, intime, status, sysphone) "
            +"VALUES(?,?,?,?,?,NOW(), 0, ?)";
    // 快递修改
    public static final String SQL_UPDATE = "UPDATE express SET number=?, username=?, company=? WHERE id=?";
    // 取件（快递状态码改变）
    public static final String SQL_UPDATE_STATUS = "UPDATE express SET status=1 WHERE code=?";
    // 删除（）
    public static final String SQL_DELETE = "DELETE FROM express WHERE id=?";

    /**
     * 用于查询数据库中的全部快递（总数+新增）， 待取件快递（总数+新增）
     *
     * @return [{size: 全部总数, day: 新增}, {size: 待取总数, day: 新增}]
     */
    @Override
    public List<Map<String, Integer>> console() {
        List<Map<String, Integer>> data = new ArrayList<>();
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_CONSOLE);

            // 3. 填充参数（可选）

            // 4. 执行SQL语句
            resultSet = state.executeQuery();

            // 5. 获取执行的结果
            if (resultSet.next()) {
                int data1_size = resultSet.getInt("data1_size");
                int data1_day = resultSet.getInt("data1_day");
                int data2_size = resultSet.getInt("data2_size");
                int data2_day = resultSet.getInt("data2_day");

                Map data1 = new HashMap();
                data1.put("data1_size", data1_size);
                data1.put("data1_day", data1_day);
                Map data2 = new HashMap();
                data1.put("data2_size", data2_size);
                data1.put("data2_day", data2_day);

                data.add(data1);
                data.add(data2);
            }

            // 6. 资源的释放
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }

        return data;
    }

    /**
     * 用于查询所有快递
     *
     * @param limit      是否分页，true：分页，false：查询所有的快递不分页
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递的集合
     */
    @Override
    public List<Express> findAll(boolean limit, int offset, int pageNumber) {
        ArrayList<Express> data = new ArrayList<>();
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            if (limit) {
                state = conn.prepareStatement(SQL_FIND_LIMIT);
                state.setInt(1, offset);
                state.setInt(2, pageNumber);
            } else {
                state = conn.prepareStatement(SQL_FIND_ALL);
            }

            // 3. 填充参数（可选）

            // 4. 执行SQL语句
            resultSet = state.executeQuery();

            // 5. 获取执行的结果
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("userPhone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                Integer status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                data.add(e);
            }
            // 6. 资源的释放
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return data;
    }

    /**
     * 根据快递单号，查询快递信息
     *
     * @param number 单号
     * @return 查询的快递信息，单号不存在时返回null
     */
    @Override
    public Express findByNumber(String number) {
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_NUMBER);

            // 3. 填充参数（可选）
            state.setString(1, number);

            // 4. 执行SQL语句
            resultSet = state.executeQuery();

            // 5. 获取执行的结果

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                //String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("userPhone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                Integer status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                return e;
            }
            // 6. 资源的释放
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return null;
    }

    /**
     * 根据取件码，查询快递信息
     *
     * @param code 取件码
     * @return 查询的快递信息，取件码不存在时返回null
     */
    @Override
    public Express findByCode(String code) {
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_CODE);

            // 3. 填充参数（可选）
            state.setString(1, code);

            // 4. 执行SQL语句
            resultSet = state.executeQuery();

            // 5. 获取执行的结果
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("userPhone");
                String company = resultSet.getString("company");
                //String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                Integer status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                return e;
            }

            // 6. 资源的释放
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return null;
    }

    /**
     * 根据用户手机号码，查询他所有的快递信息
     *
     * @param userPhone 用户手机号码
     * @return 查询的快递信息列表
     */
    @Override
    public List<Express> findByUserPhone(String userPhone) {
        ArrayList<Express> data = new ArrayList<>();
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_USERPHONE);

            // 3. 填充参数（可选）
            state.setString(1, userPhone);

            // 4. 执行SQL语句
            resultSet = state.executeQuery();

            // 5. 获取执行的结果
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                //String userPhone = resultSet.getString("userPhone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                Integer status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                data.add(e);
            }

            // 6. 资源的释放
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return data;
    }

    /**
     * 根据录入人手机号码，查询他所有的快递信息
     *
     * @param sysPhone 录入人手机号码
     * @return 查询的快递信息列表
     */
    @Override
    public List<Express> findBySysPhone(String sysPhone) {
        ArrayList<Express> data = new ArrayList<>();
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_SYSPHONE);

            // 3. 填充参数（可选）
            state.setString(1, sysPhone);

            // 4. 执行SQL语句
            resultSet = state.executeQuery();

            // 5. 获取执行的结果
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("userPhone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                Integer status = resultSet.getInt("status");
                //String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                data.add(e);
            }

            // 6. 资源的释放
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return data;
    }

    /**
     * 快递的录入
     *
     * @param e 要录入的快递对象
     * @return 录入的结果，true成功，false失败
     */
    @Override
    public boolean insert(Express e) throws DuplicateCodeException {
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_INSERT);

            // 3. 填充参数（可选）
            // INSERT INTO express
            //         (number, username, userphone, company, code, intime, status, sysphone)
            // VALUES(?,?,?,?,?,NOW(), 0, ?);
            state.setString(1, e.getNumber());
            state.setString(2, e.getUsername());
            state.setString(3, e.getUserPhone());
            state.setString(4, e.getCompany());
            state.setString(5, e.getCode());
            state.setString(6, e.getSysPhone());

            // 4. 执行SQL语句
            // resultSet = state.executeQuery();

            // 5. 获取执行的结果
            return state.executeUpdate() > 0 ? true : false;
            // 6. 资源的释放
        }
        // catch (SQLException throwables) {
        //     throwables.printStackTrace();
        // }
        catch (SQLException e1) {
            System.out.println(e1.getMessage());
            if (e1.getMessage().endsWith("for key 'express.code'")) {
                // 因为取件码重复，出现的异常
                DuplicateCodeException e2 = new DuplicateCodeException(e1.getMessage());
                throw e2;
            } else {
                e1.printStackTrace();
            }
        }
        finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return false;
    }

    /**
     * 快递的修改
     *
     * @param id         要修改的快递id
     * @param newExpress 要录入的快递对象（number, company, username, userPhone)
     * @return 录入的结果，true成功，false失败
     */
    @Override
    public boolean update(int id, Express newExpress) {
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_CONSOLE);

            // 3. 填充参数（可选）

            // 4. 执行SQL语句
            resultSet = state.executeQuery();

            // 5. 获取执行的结果

            // 6. 资源的释放
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return false;
    }

    /**
     * 更改快递的状态为1，表示取件完成
     *
     * @param number 要修改的快递id
     * @return 修改的结果，true成功，false失败
     */
    @Override
    public boolean updateStatus(String number) {
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_CONSOLE);

            // 3. 填充参数（可选）

            // 4. 执行SQL语句
            resultSet = state.executeQuery();

            // 5. 获取执行的结果

            // 6. 资源的释放
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return false;
    }

    /**
     * 根据id，删除单个快递信息
     *
     * @param id 要删除的快递id
     * @return 录入的结果，true成功，false失败
     */
    @Override
    public boolean delete(int id) {
        // 1. 获取数据库的连接
        Connection conn = DruidUtil.getConnection();

        // 2. 预编译SQL语句
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_CONSOLE);

            // 3. 填充参数（可选）

            // 4. 执行SQL语句
            resultSet = state.executeQuery();

            // 5. 获取执行的结果

            // 6. 资源的释放
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return false;
    }
}
