package com.example.lovestory.learn.singleton;

public class HungrySingleton {

    //私有参数，类加载时候进行初始化，静态对象只有一份，多个实列对象共享
    private static HungrySingleton singleton = new HungrySingleton();

    //对象构造器私有，无法创建
    private HungrySingleton() {

    }

    //暴露一个获取对象方法，只能通过这个方法获取
    //static 创建独立于对象的域变量和方法，被类的实列对象所共享
    public static HungrySingleton getSingleton() {
        return singleton;
    }
}
