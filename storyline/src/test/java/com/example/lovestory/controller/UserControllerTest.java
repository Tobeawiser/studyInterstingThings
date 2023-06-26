package com.example.lovestory.controller;

import com.alibaba.fastjson.JSON;
import com.example.lovestory.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    void hello() {
        List<User> insert = userController.insert();
        log.info("插入数据");
        System.out.println(JSON.toJSONString(insert));

    }

    /**
     * 插入数据的方式
     * 1 insert into values 用 forEach的方式
     * 2 用sqlSessionStatement执行
     */
    @Test
    void insert() {
    }


    @Test
    void insertByValues() {
    }

    @Test
    void insertBySqlSession() {

    }
}