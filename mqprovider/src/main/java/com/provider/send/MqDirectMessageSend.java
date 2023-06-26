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

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class MqDirectMessageSend {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment environment;

    public void sendObjectMsg(Object message) throws JsonProcessingException {
        String exchangeName = environment.getProperty("mq.basic.info.objecetexchange.name");
        String keyName = environment.getProperty("mq.basic.info.routing.objectkey.name");
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

    public void sendStringMsg(String message) throws JsonProcessingException {
        String exchangeName = environment.getProperty("mq.basic.info.exchange.name");
        String keyName = environment.getProperty("mq.basic.info.routing.key.name");
        log.info("exchangeName:{}", exchangeName);
        log.info("keyName:{}", keyName);

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(exchangeName);
        rabbitTemplate.setRoutingKey(keyName);

        //message转换为byte
        Message mqMessage = MessageBuilder.withBody(message.getBytes(StandardCharsets.UTF_8))
                .build();
        rabbitTemplate.convertAndSend(mqMessage);

//        rabbitTemplate.convertAndSend(mqMessage, new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                MessageProperties messageProperties = message.getMessageProperties();
//                //消息持久化
//                messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
//                //设置消息头  指定类实列
//                messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,String.class);
//                return null;
//            }
//        });
    }

}
