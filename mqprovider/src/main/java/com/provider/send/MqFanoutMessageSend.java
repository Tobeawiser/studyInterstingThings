package com.provider.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqFanoutMessageSend {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment environment;

    public void sendObjectMsg(Object message) throws JsonProcessingException {
        String exchangeName = environment.getProperty("mq.basic.info.fanout.exchange");
        String keyName = environment.getProperty("mq.basic.info.fanout.bindkey");
        log.info("exchangeName:{}", exchangeName);
        log.info("keyName:{}", keyName);

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(exchangeName);
        rabbitTemplate.setRoutingKey(keyName);

        //message转换为byte
        byte[] bytes = objectMapper.writeValueAsBytes(message);

        Message mqMessage = MessageBuilder.withBody(bytes)
                .build();
        rabbitTemplate.convertAndSend(mqMessage);
    }

}
