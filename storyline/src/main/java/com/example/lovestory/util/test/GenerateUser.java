package com.example.lovestory.util.test;

import com.example.lovestory.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GenerateUser {
    public static List<User> randomCreateUserInfo(int count) {
        List<User> arrayList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = User.builder()
                    .id(new Random().nextInt())
                    .name("name" + UUID.randomUUID().toString().substring(0, 4))
                    .age(String.valueOf(Math.random() * 100).substring(0, 2))
                    .build();
            arrayList.add(user);
        }
        return arrayList;


    }
}
