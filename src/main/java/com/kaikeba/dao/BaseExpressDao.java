package com.kaikeba.dao;

import com.kaikeba.bean.Express;
import com.kaikeba.exception.DuplicateCodeException;

import java.util.List;
import java.util.Map;

public interface BaseExpressDao {
    /**
     * 用于查询数据库中的全部快递（总数+新增）， 待取件快递（总数+新增）
     * @return [{size: 全部总数, day: 新增}, {size: 待取总数, day: 新增}]
     */
    List<Map<String, Integer>> console();

    /**
     * 用于查询所有快递
     * @param limit         是否分页，true：分页，false：查询所有的快递不分页
     * @param offset        SQL语句的起始索引
     * @param pageNumber    页查询的数量
     * @return              快递的集合
     */
    List<Express> findAll(boolean limit, int offset, int pageNumber);

    /**
     * 根据快递单号，查询快递信息
     * @param number    单号
     * @return          查询的快递信息，单号不存在时返回null
     */
    Express findByNumber(String number);

    /**
     * 根据取件码，查询快递信息
     * @param code      取件码
     * @return          查询的快递信息，取件码不存在时返回null
     */
    Express findByCode(String code);

    /**
     * 根据用户手机号码，查询他所有的快递信息
     * @param userPhone     用户手机号码
     * @return              查询的快递信息列表
     */
    List<Express> findByUserPhone(String userPhone);

    /**
     * 根据录入人手机号码，查询他所有的快递信息
     * @param sysPhone      录入人手机号码
     * @return              查询的快递信息列表
     */
    List<Express> findBySysPhone(String sysPhone);

    /**
     * 快递的录入
     * @param e         要录入的快递对象
     * @return          录入的结果，true成功，false失败
     */
    boolean insert(Express e) throws DuplicateCodeException;

    /**
     * 快递的修改
     * @param id            要修改的快递id
     * @param newExpress    要录入的快递对象（number, company, username, userPhone)
     * @return              录入的结果，true成功，false失败
     */
    boolean update(int id, Express newExpress);

    /**
     * 更改快递的状态为1，表示取件完成
     * @param code        要修改的快递id
     * @return              修改的结果，true成功，false失败
     */
    boolean updateStatus(String code);

    /**
     * 根据id，删除单个快递信息
     * @param id    要删除的快递id
     * @return      录入的结果，true成功，false失败
     */
    boolean delete(int id);
}
