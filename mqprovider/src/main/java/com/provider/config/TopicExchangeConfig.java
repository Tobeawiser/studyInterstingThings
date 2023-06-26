package com.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class TopicExchangeConfig {

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("fanoutQueueA")
    private Queue fanoutQueueA;
    @Autowired
    @Qualifier("fanoutQueueB")
    private Queue fanoutQueueB;

    @Bean
    public TopicExchange topicExchange() {
        String topicExchange = env.getProperty("mq.basic.info.fanout.topicexchange");
        return new TopicExchange(topicExchange, true, false);
    }

    @Bean
    public Binding bindExchangeA() {
        String bindKeya = env.getProperty("mq.basic.info.fanout.bindkeya");
        return BindingBuilder
                .bind(fanoutQueueA)
                .to(topicExchange())
                .with(bindKeya);
    }

    @Bean
    public Binding bindExchangeB() {
        String bindKeyb = env.getProperty("mq.basic.info.fanout.bindkeyb");
        return BindingBuilder
                .bind(fanoutQueueB)
                .to(topicExchange())
                .with(bindKeyb);
    }
}
