package com.consumer.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    //定义日志 todo

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
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(10);
        factory.setMaxConcurrentConsumers(15);
        //实例拉去的消息数量
        factory.setPrefetchCount(10);
        return factory;
    }


}
