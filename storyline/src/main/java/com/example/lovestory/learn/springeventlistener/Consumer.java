package com.example.lovestory.learn.springeventlistener;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
public class Consumer implements ApplicationListener<LoginEvent> {

    @Override
    public void onApplicationEvent(LoginEvent event) {
        System.out.println("事件驱动接收消息");
        System.out.println("正在处理......");
        System.out.println("处理完成......");
    }
}
