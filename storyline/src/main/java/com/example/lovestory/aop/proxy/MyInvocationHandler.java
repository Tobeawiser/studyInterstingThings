package com.example.lovestory.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 动态代理需要一个接口，用InvocationHandler 进行拦截生成代理对象增强
 * 泛型加入，获取代理对象传入参数
 */
public class MyInvocationHandler<T> implements InvocationHandler {

    private T target;

    public MyInvocationHandler(T inputTarget) {
        super();
        this.target = inputTarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before add");
        Object result = method.invoke(target, args);
        System.out.println("after add");
        return result;
    }

    /**
     * this: 包含拦截器方法的代理对象
     *
     * @return Object
     */
    public T getProxy() {
        Object proxyObj = Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
        return (T) proxyObj;
    }
}
