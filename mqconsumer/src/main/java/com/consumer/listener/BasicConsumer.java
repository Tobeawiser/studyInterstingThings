package com.consumer.listener;

import com.entity.MqUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class BasicConsumer {

    @Autowired
    public ObjectMapper objectMapper;

    @RabbitListener(queues = "object_queue", containerFactory = "singleListenerContainer")
    public void consumeMsg(MqUser mqUser) {
        //MqUser mqUser = objectMapper.readValue(msg, MqUser.class);
        //String message = new String(msg, "UTF-8");
        try {
            log.info("mqUser:{}", objectMapper.writeValueAsString(mqUser));
        } catch (JsonProcessingException e) {
            log.error("转换出错{}", e);
        }
    }

    @RabbitListener(queues = "queue_name", containerFactory = "singleListenerContainer")
    public void consumeStringMsg(Message message, Channel channel) {
        MessageProperties messageProperties = message.getMessageProperties();
        //获取分发时唯一标识
        long deliveryTag = messageProperties.getDeliveryTag();
        //获取消息
        byte[] body = message.getBody();
        //转换
        try {
            String msg = String.valueOf(body);
            System.out.println(msg);
            //String message = new String(msg, "UTF-8");
            log.info("mqUser:{}", msg);

            //手动确认 避免消息重复消费
            channel.basicAck(deliveryTag, true);
            log.info("手动确认成功{}", deliveryTag);
        } catch (Exception e) {
            log.error("手动确认异常", e);
            try {
                channel.basicReject(deliveryTag, false);
                log.error("手动拒绝成功{}", deliveryTag);
            } catch (IOException ioException) {
                log.error("拒绝出错", ioException);
            }

        }

    }
}
