package com.example.lovestory.aop.proxy;

public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("add Method");
    }
}
