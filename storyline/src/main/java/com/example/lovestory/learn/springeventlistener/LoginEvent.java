package com.example.lovestory.learn.springeventlistener;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;
import java.time.Clock;

public class LoginEvent extends ApplicationEvent implements Serializable {

    private String userName;
    private String ip;

    public LoginEvent(Object source) {
        super(source);
    }

    public LoginEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public LoginEvent(Object source, String userName, String ip) {
        super(source);
        this.userName = userName;
        this.ip = ip;
    }
}
