package com.example.lovestory.learn.nio;

import com.example.lovestory.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroupBy {

    public static void main(String[] args) {
        int i = 0;
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        users.add(new User());
        users.forEach(user -> {
            user.setAge("18");
            user.setId(i + 1);
            user.setName("name" + i);
        });

        Map<String, List<User>> collect = users.stream()
                .collect(Collectors.groupingBy(
                        u -> u.getName() + u.getId()
                ));

        System.out.println(collect);
    }
}
