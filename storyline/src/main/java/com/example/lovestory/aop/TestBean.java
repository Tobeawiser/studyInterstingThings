package com.example.lovestory.aop;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test(String arg) {
        System.out.println("参数为：" + arg);
        System.out.println("testMethod");
    }
}
