package com.example.lovestory.learn.springeventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void sendMessage() {
        LoginEvent loginEvent = new LoginEvent(this, "lyle", "localhostIp");
        applicationEventPublisher.publishEvent(loginEvent);
        System.out.println("Producer：已经发送事件");
    }
}
