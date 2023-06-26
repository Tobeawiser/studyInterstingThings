package com.provider.send;

import com.entity.DeadInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqDeadMessageSend {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment environment;

    public void sendMsg(DeadInfo deadInfo) {
        try {
            String exchangeName = environment.getProperty("mq.basic.info.dead.topicexchange");
            String keyName = environment.getProperty("mq.basic.info.dead.bindkey");
            log.info("exchangeName:{}", exchangeName);
            log.info("keyName:{}", keyName);

            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(exchangeName);
            rabbitTemplate.setRoutingKey(keyName);

            //message转换为byte
            //message转换为byte
            byte[] bytes = objectMapper.writeValueAsBytes(deadInfo);

            Message mqMessage = MessageBuilder.withBody(bytes)
                    .build();

            rabbitTemplate.convertAndSend(mqMessage, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties messageProperties = message.getMessageProperties();
                    //消息持久化
                    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    //设置消息头  指定类实列
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, DeadInfo.class);
                    //消息和队列同时设置ttl时间 取较短的值 todo 时间设置是否只对死信队列生效
                    messageProperties.setExpiration(String.valueOf(10000));
                    return message;
                }
            });

            log.info("死信队列发送消息成功{}", deadInfo.toString());
        } catch (Exception e) {
            log.info("死信队列发送消息失败{}{}", deadInfo.toString(), e);
        }

    }

}
