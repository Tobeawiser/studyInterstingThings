package com.example.lovestory.aop.proxy;

import com.example.lovestory.learn.springeventlistener.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringEventHandleTest {

    @Autowired
    private Producer producer;

    @Test
    void add() {
        producer.sendMessage();
    }
}