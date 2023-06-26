package com.example.lovestory.util.test.sort;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HashSort {

    public static void main(String[] args) {
        Random random = new Random();
        Map map = new HashMap();
        map.put("hello", "1");
        map.put("hello", "2");
        map.put("hello", "3");
        map.put("hello1", "4");
        map.put("hello2", "5");
        Assert.notNull(map, "");
    }
}
