package com.example.lovestory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lovestory.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao extends BaseMapper<User> {

    <E> void insertValues(List<E> list);

    void insertMultiSql(List<User> users);

    void insertException(List<User> users);
}
