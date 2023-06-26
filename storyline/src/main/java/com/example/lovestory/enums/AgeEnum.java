package com.example.lovestory.enums;

public enum AgeEnum {

    TWETWY("20", "二十岁"),
    FOURTY("40", "四十岁"),
    SIXTY("60", "六十岁");

    private String val;
    private String mark;

    AgeEnum(String val, String mark) {
        this.val = val;
        this.mark = mark;
    }

    public String getVal() {
        return val;
    }

    public String getMark() {
        return mark;
    }
}
