package com.kaikeba.dao.imp;

import com.kaikeba.bean.User;
import com.kaikeba.dao.BaseUserDao;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoMysqlTest {
    BaseUserDao dao = new UserDaoMysql();

    @Test
    void findByUserPhone() {
        User user = dao.findByUserPhone("18888888888");
        System.out.println("user = " + user);
        assertNotNull(user);
    }

    @Test
    void insert() {
        Boolean insert = dao.insert(new User("詹姆斯", "18348965772", "741963855625463147", "741953"));
        assertTrue(insert);
    }

    @Test
    void insertTest() {
        Boolean insert = dao.insert(new User("雷阿伦", "1336548528", "741243857525463161", "148653"));
        assertFalse(insert);
    }

    @Test
    void insertFor() {
        Boolean insert;
        int i = 0;
        int count = 5;
        for (; i < count; i++) {
            User user = new User("巴蒂尔" + i, "1823634452" + i, "7412316175252438" + i, "741359");
            insert = dao.insert(user);
            if (!insert) {
                break;
            }
        }
        assertEquals(i, count);
    }

    @Test
    void update() {
        Boolean update = dao.update(new User(7, "加内特", "1836548753", "654296254585463528", "148364"));
        assertTrue(update);
    }

    @Test
    void delete() {
        Boolean delete = dao.delete(new User(10));
        assertTrue(delete);
    }

    @Test
    void findAll() {
        List<User> all = dao.findAll(false, 0, 0);
        for (User user : all) {
            System.out.println("user.id = " + user.getId() + ", nickName = " + user.getNickName());
        }
    }

    @Test
    void findAllWithLimit() {
        List<User> all = dao.findAll(true, 2, 2);
        for (User user : all) {
            System.out.println("user.id = " + user.getId() + ", nickName = " + user.getNickName());
        }
    }

    @Test
    void count() {
        Integer count = dao.count(-1);
        System.out.println("count = " + count);

        count = dao.count(3);
        System.out.println("count = " + count);
    }
}