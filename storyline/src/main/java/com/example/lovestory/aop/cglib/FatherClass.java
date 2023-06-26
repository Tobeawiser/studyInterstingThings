package com.example.lovestory.aop.cglib;

public class FatherClass {


    public static void makeCook(String meat) {
        System.out.println("add food:" + meat);
        System.out.println("make cooking for food");
    }

    public void fireWater(String water) {
        System.out.println("add water:" + water);
        System.out.println("fire water");
    }
}
