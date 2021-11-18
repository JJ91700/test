package com.kaikeba.service;

import com.kaikeba.bean.Express;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressServiceTest {

    @Test
    public void insert() {
        Express express = new Express("1233", "李伟杰", "18516955565", "顺丰快递", "18888777755", "666666");
        boolean insert = ExpressService.insert(express);
        System.out.println("insert = " + insert);
    }
}