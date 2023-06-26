package com.example.lovestory.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lovestory.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    <E> void insertByForEach(List<E> list);

    <E> void insertByValues(List<E> list);

    <E> void insertBySqlSession(List<E> list);

    void insertMultiSql(List<User> users);
}
