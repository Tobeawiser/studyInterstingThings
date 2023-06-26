package com.example.lovestory.learn.generic;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {

    public static void main(String[] args) {
        //泛型擦除，需要泛型类参数部分指定上下限
        List<? extends ArrayList> strings = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        System.out.println(strings.getClass());
        System.out.println(integers.getClass());
        boolean b = strings.getClass() == integers.getClass();
        System.out.println(b);
    }


}
