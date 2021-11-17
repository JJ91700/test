package com.kaikeba.dao.imp;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;

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
        return null;
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
        return null;
    }

    /**
     * 根据快递单号，查询快递信息
     *
     * @param number 单号
     * @return 查询的快递信息，单号不存在时返回null
     */
    @Override
    public Express findByNumber(String number) {
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
        return null;
    }

    /**
     * 根据录入人手机号码，查询他所有的快递信息
     *
     * @param sysPhone 录入人手机号码
     * @return 查询的快递信息列表
     */
    @Override
    public List<Express> findBySysPhone(String sysPhone) {
        return null;
    }

    /**
     * 快递的录入
     *
     * @param e 要录入的快递对象
     * @return 录入的结果，true成功，false失败
     */
    @Override
    public boolean insert(Express e) {
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
        return false;
    }
}
