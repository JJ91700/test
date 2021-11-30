package com.kaikeba.dao.imp;

import com.kaikeba.bean.Courier;
import org.junit.Assert;
import org.junit.Test;


import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CourierDaoMysqlTest {

    CourierDaoMysql dao = new CourierDaoMysql();

    @Test
    public void findByUserPhone() {
        Courier courier = dao.findByUserPhone("18888888888");
        System.out.println("courier = " + courier);
    }

    @Test
    public void insert() {
        int i = 0;
        int max = 5;
        for (; i < max; i++) {
            //Integer id, String userName, String userPhone, String cardId, String password, Integer sendExpress, Timestamp
            //createTime, Timestamp loginTime, String loginIp, Integer admin
            Date date = new Date();
            Courier courier = new Courier(10 + i, "周八" + i, "18755555532",
                    "446123456789", "741852", 0, new java.sql.Timestamp(date.getTime()),
                    new java.sql.Timestamp(date.getTime()), "127.0.0.1", 0);
            Boolean insert = dao.insert(courier);
            if (!insert) {
                System.out.println("insert = " + insert);
                break;
            }
        }
        System.out.println("i = " + i + ", max = " + max);
    }

    @Test
    public void update() {
        Courier oldCourier = new Courier();
        oldCourier.setUserPhone("18755555532");
        Courier newCourier = new Courier("吴九", "15266666674", "452123456789", "852963");
        Boolean update = dao.update(oldCourier, newCourier);
        System.out.println("update = " + update);
    }

    @Test
    public void delete() {
        Courier courier = new Courier();
        courier.setUserPhone("18755555534");
        Boolean delete = dao.delete(courier);
        System.out.println("delete = " + delete);
    }

    @Test
    public void findAllWithouLimit() {
        List<Courier> all = dao.findAll(false, 1, 3);
        for (Courier courier : all) {
            System.out.println("courier[" + courier.getId() + "].getUserPhone() = " + courier.getUserPhone());
        }
    }

    @Test
    public void findAllWithLimit() {
        List<Courier> all = dao.findAll(true, 1, 3);
        for (Courier courier : all) {
            System.out.println("courier[" + courier.getId() + "].getUserPhone() = " + courier.getUserPhone());
        }
    }

    @Test
    public void countAll() {
        Integer count = dao.count(-1);
        System.out.println("count = " + count);
    }

    @Test
    public void countToday() {
        Integer count = dao.count(0);
        System.out.println("count = " + count);
    }

    @Test
    public void countYesterdayToNow() {
        Integer count = dao.count(1);
        System.out.println("count = " + count);
    }

    @Test
    public void countTheDayBeforeYesterdayToNow() {
        Integer count = dao.count(2);
        System.out.println("count = " + count);
    }

    @Test
    public void addAdmin() {
        Boolean addAdmin = dao.addAdmin("18755555533");
        System.out.println("addAdmin = " + addAdmin);
    }

    @Test
    public void removeAdmin() {
        Boolean removeAdmin = dao.removeAdmin("18755555533");
        System.out.println("removeAdmin = " + removeAdmin);
    }
}