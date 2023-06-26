package com.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FanoutExchangeConfig {

    @Autowired
    private Environment env;

    @Bean("fanoutQueueA")
    public Queue fanoutQueueA() {
        String queueName = env.getProperty("mq.basic.info.fanout.queuename");
        return new Queue(queueName, true);
    }


    @Bean("fanoutQueueB")
    public Queue fanoutQueueB() {
        String queueName = env.getProperty("mq.basic.info.fanout.queuenameb");
        return new Queue(queueName, true);
    }


    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange() {
        String exchange = env.getProperty("mq.basic.info.fanout.exchange");
        return new FanoutExchange(exchange, true, false);
    }

    @Bean("fanoutBindingA")
    public Binding fanoutBinding() {
        //广播交换机不用rountingkey
        //String bindKey = env.getProperty("mq.basic.info.fanout.bindkey");
        Binding binding = BindingBuilder
                .bind(fanoutQueueA())
                .to(fanoutExchange());
        return binding;
    }

    @Bean("fanoutBindingB")
    public Binding fanoutBindingB() {
        //广播交换机不用rountingkey
        //String bindKey = env.getProperty("mq.basic.info.fanout.bindkey");
        Binding binding = BindingBuilder
                .bind(fanoutQueueB())
                .to(fanoutExchange());
        return binding;
    }


}
