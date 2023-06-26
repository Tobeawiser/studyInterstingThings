package com.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DirectExchangeConfig {


    @Autowired
    private Environment environment;

    /**
     * 创建队列
     *
     * @return
     */
    @Bean(name = "basicQueue")
    public Queue basicQueue() {
        String property = environment.getProperty("mq.basic.info.queue.name");
        return new Queue(property, true);
    }

    /**
     * 创建交换机
     *
     * @return
     */
    @Bean
    public DirectExchange basicExchange() {
        String property = environment.getProperty("mq.basic.info.exchange.name");
        return new DirectExchange(property, true, false);
    }


    /**
     * 创建绑定
     *
     * @return
     */
    @Bean
    public Binding basicBinding() {
        String property = environment.getProperty("mq.basic.info.routing.key.name");
        Binding binding = BindingBuilder
                .bind(basicQueue())
                .to(basicExchange())
                .with(property);
        return binding;
    }


    /**
     * 创建对象队列
     *
     * @return
     */
    @Bean(name = "objectQueue")
    public Queue objectQueue() {
        String property = environment.getProperty("mq.basic.info.objectqueue.name");
        return new Queue(property, true);
    }

    /**
     * 创建交换机
     *
     * @return
     */
    @Bean
    public DirectExchange objectExchange() {
        String property = environment.getProperty("mq.basic.info.objecetexchange.name");
        return new DirectExchange(property, true, false);
    }


    /**
     * 创建绑定
     *
     * @return
     */
    @Bean
    public Binding objectBinding() {
        String property = environment.getProperty("mq.basic.info.routing.objectkey.name");
        Binding binding = BindingBuilder
                .bind(objectQueue())
                .to(objectExchange())
                .with(property);
        return binding;
    }


}
