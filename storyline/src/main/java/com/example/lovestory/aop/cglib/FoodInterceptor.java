package com.example.lovestory.aop.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class FoodInterceptor implements MethodInterceptor {

    //加入横切逻辑
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("do things begin");
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("do things finish");
        return result;
    }
}
