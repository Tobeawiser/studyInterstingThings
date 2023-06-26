package com.example.lovestory.util.test.optional;

import com.example.lovestory.entity.User;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Optional optional = Optional.of("sss");
        Optional emptyOpt = Optional.of("");
        //此给构造器不能构造null值
        //Optional nullOpt = Optional.of(null);
        Optional optionalS = Optional.ofNullable("sss");
        Optional optionalS1 = Optional.ofNullable("");
        Optional optionalS2 = Optional.ofNullable(null);

        //optional 对空值常用获取方法
        //泛型的入参或者出参  维持一致也就可以
        //也可以对 对象使用
        //并且可对其进行函数式接口调用
        String val = "val";
        String other = Optional.ofNullable(val).orElse("other");
        System.out.println(other);

        User user = new User();
        user.setAge("18");
        Optional.ofNullable(user).ifPresent(u -> System.out.println(u.getAge()));


    }
}
