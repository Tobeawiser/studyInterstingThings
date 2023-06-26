package com.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadExchangeConfig {

    @Autowired
    private Environment env;


    @Bean
    public Queue deadTopicQueue() {
        Map<String, Object> args = new HashMap<>();
        String deadExchange = env.getProperty("mq.basic.info.dead.deadexchange");
        String routingKey = env.getProperty("mq.basic.info.dead.binddeadkeya");
        //todo 必须这样写吗
        args.put("x-dead-letter-exchange", deadExchange);
        args.put("x-dead-letter-routing-key", routingKey);
        args.put("x-message-ttl", 5000);

        String queueName = env.getProperty("mq.basic.info.dead.queuename");
        return new Queue(queueName, true, false, false, args);
    }

    /**
     * 基本交换机
     *
     * @return
     */
    @Bean
    public DirectExchange baseTopicExchange() {
        String exchange = env.getProperty("mq.basic.info.dead.topicexchange");
        return new DirectExchange(exchange, true, false);
    }


    @Bean
    public Binding bindToDeadLetter() {
        String baseKey = env.getProperty("mq.basic.info.dead.binddeadkeya");
        return BindingBuilder
                .bind(deadTopicQueue())
                .to(baseTopicExchange())
                .with(baseKey);
    }

    @Bean
    public Queue realQueue() {
        String realQueue = env.getProperty("mq.basic.info.dead.realqueue");
        return new Queue(realQueue, true);
    }

    @Bean
    public DirectExchange deadExchange() {
        String changeName = env.getProperty("mq.basic.info.dead.deadexchange");
        return new DirectExchange(changeName, true, false);
    }

    @Bean
    public Binding deadBinding() {
        String routingKey = env.getProperty("mq.basic.info.dead.binddeadkeya");

        return BindingBuilder.bind(realQueue())
                .to(deadExchange())
                .with(routingKey);

    }

}
