package com.consumer.listener;

import com.entity.MqUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class FanountConsumer {

    @Autowired
    public ObjectMapper objectMapper;


    @RabbitListener(queues = "AfanoutA", containerFactory = "singleListenerContainer")
    public void consumeMsgQueuename(MqUser user) throws IOException {
        System.out.println(user);
        //String message = new String(msg, "UTF-8");
        log.info("mqUser:{}", user);
    }

    @RabbitListener(queues = "fanoutB", containerFactory = "singleListenerContainer")
    public void consumeMsgQueuenameB(MqUser user) throws IOException {
        System.out.println(user);
        //String message = new String(msg, "UTF-8");
        log.info("mqUser:{}", user);
    }
}
