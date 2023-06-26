package com.provider.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMqConfig {
    //定义日志 todo

    @Autowired
    private ObjectMapper objectMapper;

    //自动装配mq链接工厂实例
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    //自动装配消息监听器所在容器工程配置类实例
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    //单一消费者实例配置
    @Bean("singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置ack模式
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        //实例拉去的消息数量
        factory.setPrefetchCount(1);
        return factory;
    }

    //多个消费者实例配置
    @Bean("multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, cachingConnectionFactory);
        //factory.setConnectionFactory(cachingConnectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(10);
        factory.setMaxConcurrentConsumers(15);
        //实例拉去的消息数量
        factory.setPrefetchCount(10);
        return factory;
    }

    //rabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate() {
        //生产者确认机制  已弃用
        cachingConnectionFactory.setPublisherConfirms(true);

        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
        //消息发送成功时返回反馈信息
        cachingConnectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);

        //生产者确认机制  设置消息发送确认机制，发送成功时打印日志 不管成功于否，都会调用
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                try {
                    log.info("消息发送成功:correlationData{},ack{},cause{}",
                            objectMapper.writeValueAsString(correlationData),
                            ack,
                            cause);
                } catch (JsonProcessingException e) {
                    log.info("e:", e);
                }

            }
        });
        //消息发送成功时返回反馈信息 如果消息未从路由成功发送到队列那么会走这个回调，这里会把消息的整个明细返回 todo 待测试
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                try {
                    log.info("消息丢失：returnedMessage:{},", objectMapper.writeValueAsString(returnedMessage));
                } catch (JsonProcessingException e) {
                    log.error("转换异常:", e);
                }

            }
        });

        return rabbitTemplate;
    }


}
