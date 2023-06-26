package com.example.lovestory.service.impl;

import com.example.lovestory.dao.UserDao;
import com.example.lovestory.entity.User;
import com.example.lovestory.service.UserService;
import com.example.lovestory.util.test.GenerateUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class UserServiceImplTest {
    int USER_COUNT = 10 * 1000;
    @Autowired
    private UserService userService;
    private UserDao userDao;

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }

    @Test
    void insertByForEach() {
        userService.insertByForEach(getUsers());
    }

    @Test
    void insertByValues() {
        userService.insertByValues(getUsers());
    }

    @Test
    void insertBySqlSession() {
        userService.insertBySqlSession(getUsers());
    }

    @Test
    void insertMultiSql() {
        List<User> users = getUsers();
        List<User> usersSub = users.subList(0, 2);
        userService.insertMultiSql(usersSub);
    }

    @Test
    void baseService() {
        long start = System.currentTimeMillis();
        userService.saveBatch(getUsers());
        long end = System.currentTimeMillis();
        System.out.println("执行耗时：s:");
        System.out.println((end - start) / 1000);
    }

    List<User> getUsers() {
        return GenerateUser.randomCreateUserInfo(USER_COUNT);
    }
}