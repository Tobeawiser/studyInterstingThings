package com.example.lovestory.aop.proxy;

public class TestProxy {


    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        MyInvocationHandler<UserService> invocationHandler = new MyInvocationHandler<>(userService);
        UserService proxy = invocationHandler.getProxy();
        proxy.add();
    }
}
