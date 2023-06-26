package com.consumer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class DeadConsumer {

    @Autowired
    public ObjectMapper objectMapper;

    @RabbitListener(queues = "realQueueNameAA", containerFactory = "singleListenerContainer")
    public void consumeMsg(Message message, Channel channel) {
        MessageProperties messageProperties = message.getMessageProperties();
        //获取分发时唯一标识
        long deliveryTag = messageProperties.getDeliveryTag();
        //获取消息
        byte[] body = message.getBody();
        //转换
        try {
            String msg = String.valueOf(body);
            String header = messageProperties.getHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME);
            Class<?> headerClass = Class.forName(header);
            //Class.forName()
            Object o = objectMapper.readValue(message.getBody(), headerClass);
            log.info("DeadInfo:{}", o.toString());

            //手动确认 避免消息重复消费
            channel.basicAck(deliveryTag, true);
            log.info("死信队列手动确认成功{}", deliveryTag);
        } catch (Exception e) {
            log.error("死信队列手动确认异常", e);
            try {
                channel.basicReject(deliveryTag, false);
                log.error("手动拒绝成功{}", deliveryTag);
            } catch (IOException ioException) {
                log.error("拒绝出错", ioException);
            }

        }
    }
}
