package com.example.lovestory.learn.singleton;

public class LazySingletonLock {

    //私有参数，静态对象只有一份，多个实列对象共享
    //不进行初始化，在获取对象时进行初始化
    private static LazySingletonLock singleton;

    //对象构造器私有，无法创建
    private LazySingletonLock() {
    }

    //暴露一个获取对象方法，只能通过这个方法获取
    //static 创建独立于对象的域变量和方法，被类的实列对象所共享
    //synchronized 防止创建对象并发风险
    public static synchronized LazySingletonLock getSingleton() {
        if (singleton == null) {
            singleton = new LazySingletonLock();
        }
        return singleton;
    }

    public void method1() {
        System.out.println("method1");
    }
}
