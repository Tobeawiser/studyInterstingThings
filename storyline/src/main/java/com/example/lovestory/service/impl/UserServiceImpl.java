package com.example.lovestory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lovestory.dao.UserDao;
import com.example.lovestory.entity.User;
import com.example.lovestory.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public <E> void insertByForEach(List<E> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        if (list.get(0) instanceof User) {
            list.forEach(a -> userDao.insert((User) a));
        }

    }

    @Override
    public <E> void insertByValues(List<E> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        userDao.insertValues(list);

    }

    @Override
    public <E> void insertBySqlSession(List<E> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);

        try {
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            if (list.get(0) instanceof User) {
                for (int i = 0; i < list.size(); i++) {
                    mapper.insert((User) list.get(i));
                    if (i % 2000 == 0) {
//                        //直接写入数据库中 且提交事物
//                        sqlSession.flushStatements();
                    }
                    if (i == 2002) {
                        int s = 1 / 0;
                    }
                }

                sqlSession.commit();
                sqlSession.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }


    }

    @Override
    public void insertMultiSql(List<User> users) {
        userDao.insertMultiSql(users);
    }
}
