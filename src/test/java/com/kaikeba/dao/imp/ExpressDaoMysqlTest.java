package com.kaikeba.dao.imp;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.exception.DuplicateCodeException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressDaoMysqlTest {

    @Test
    public void console() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByNumber() {
    }

    @Test
    public void findByCode() {
    }

    @Test
    public void findByUserPhone() {
    }

    @Test
    public void findBySysPhone() {
    }

    @Test
    public void insert() {
        // String number, String username, String userPhone, String company, String code, String sysPhone
        BaseExpressDao dao = new ExpressDaoMysql();
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
    public void update() {
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void delete() {
    }
}