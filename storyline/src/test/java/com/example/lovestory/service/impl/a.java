package com.example.lovestory.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class a {
    public static void main(String[] args) {
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(localDateTime);

    }
}