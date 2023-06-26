package com.example.lovestory.entity;

public class UserTT {

    static int x = 10;

    static {
        x += 5;
    }

    static {
        x /= 5;
    }

    public static void main(String[] args) {
        System.out.println(x);
    }
}
