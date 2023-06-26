package com.example.lovestory.aop.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class ProxyStrengthen<T> {

    public static void stengthen() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FatherClass.class);
        enhancer.setCallback(new FoodInterceptor());
        FatherClass f = (FatherClass) enhancer.create();
        f.fireWater("water");
    }

    /**
     * T 返回增强对象
     *
     * @param fatherClass fatherClass
     * @param interceptor interceptor
     */
    public T stengthen(T fatherClass, MethodInterceptor interceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(fatherClass.getClass());
        enhancer.setCallback(interceptor);
        return (T) enhancer.create();
    }
}
