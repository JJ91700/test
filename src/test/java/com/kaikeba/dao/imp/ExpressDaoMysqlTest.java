package com.kaikeba.dao.imp;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.exception.DuplicateCodeException;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ExpressDaoMysqlTest {

    BaseExpressDao dao = new ExpressDaoMysql();

    @Test
    public void console() {
        List<Map<String, Integer>> console = dao.console();
        System.out.println("console = " + console);
    }

    @Test
    public void findAll() {
        // TODO:传参上下界限，超过了上下限该限制？
        List<Express> all = dao.findAll(true, 1, 3);
//        System.out.println("all = " + all);
        for (Express express : all) {
            System.out.println("express.getCode() = " + express.getCode());
        }
        System.out.println();
        all = dao.findAll(false, 0, 0);
//        System.out.println("all = " + all);
        for (Express express : all) {
            System.out.println("express.getCode() = " + express.getCode());
        }
    }

    @Test
    public void findByNumber() {
        Express e = dao.findByNumber("" + 124);
        System.out.println("e = " + e);
    }

    @Test
    public void findByCode() {
        Express e = dao.findByCode("000000");
        System.out.println("e = " + e);
    }

    @Test
    public void findByUserPhone() {
        List<Express> byUserPhone = dao.findByUserPhone("13843838438");
//        System.out.println("byUserPhone = " + byUserPhone);
        for (Express express : byUserPhone) {
            System.out.println("express.getCode() = " + express.getCode());
        }
        System.out.println();
        byUserPhone = dao.findByUserPhone("13843838338");
//        System.out.println("byUserPhone = " + byUserPhone);
        for (Express express : byUserPhone) {
            System.out.println("express.getCode() = " + express.getCode());
        }
    }

    @Test
    public void findBySysPhone() {
        List<Express> bySysPhone = dao.findBySysPhone("12345678910");
        for (Express express : bySysPhone) {
            System.out.println("express.getId() = " + express.getCode());
        }
//        System.out.println("bySysPhone = " + bySysPhone);
        System.out.println();
        bySysPhone = dao.findBySysPhone("18888888888");
//        System.out.println("bySysPhone = " + bySysPhone);
        for (Express express : bySysPhone) {
            System.out.println("express.getId() = " + express.getCode());
        }
    }

    @Test
    public void insert() {
        // String number, String username, String userPhone, String company, String code, String sysPhone
        Express e = new Express("123123123", "李四", "13843838338", "顺丰快递", "464635","18888888888");
        boolean insert = false;
        try {
            insert = dao.insert(e);
        } catch (DuplicateCodeException duplicateCodeException) {
            System.out.println("取件码重复的异常被捕获到了");
        }
        System.out.println("insert = " + insert);
    }

    @Test
    public void insert2() {
        // String number, String username, String userPhone, String company, String code, String sysPhone
        boolean insert = false;
        try {
            for (int i = 0; i < 10; i++) {
                Express e = new Express("12311" + i, "李伟杰", "13660200576", "申通快递", 666000 + i + "","18888888888");
                insert = dao.insert(e);
                if (insert != true) {
                    System.out.println("e = " + e);
                    break;
                }
            }
        } catch (DuplicateCodeException duplicateCodeException) {
            System.out.println("取件码重复的异常被捕获到了");
        }
        System.out.println("insert = " + insert);
    }

    @Test
    public void update() {
        Express express = new Express();
        express.setNumber("321");
        express.setCompany("天天快递");
        express.setUsername("王二");
        express.setStatus(1);
        boolean update = dao.update(1, express);
        if (update) {
            System.out.println("update ok");
        } else {
            System.out.println("update ng");
        }
    }

    @Test
    public void updateStatus() {
        boolean b = dao.updateStatus("000000");
        System.out.println("b = " + b);
    }

    @Test
    public void delete() {
        boolean delete = dao.delete(6);
        System.out.println("delete = " + delete);
    }
}