package com.lyle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jemeter")
public class JemeterController {
    public int i = 0;

    @GetMapping("/test")
    public String testJemeter() {
        String name = Thread.currentThread().getName();

        System.out.println("--------" + name + "--------");
        i++;
        System.out.println("请求已经到" + i);
        return "hello";
    }

    @PostMapping("/test")
    public String testJemeter(@RequestParam("key") String key) {
        return key;
    }

}
