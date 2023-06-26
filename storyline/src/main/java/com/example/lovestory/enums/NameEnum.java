package com.example.lovestory.enums;

public enum NameEnum {

    TWETWY("lyle", "名字lyle"),
    FOURTY("congcong", "名字cc"),
    SIXTY("story", "名字lyleStory");

    private String val;
    private String nameDesc;

    NameEnum(String val, String nameDesc) {
        this.val = val;
        this.nameDesc = nameDesc;
    }

    public String getVal() {
        return val;
    }

    public String getNameDesc() {
        return nameDesc;
    }
}
