package com.example.lovestory.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {
    int core = Runtime.getRuntime().availableProcessors();

    @Bean
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return new ThreadPoolExecutor(
                core,
                20,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(20),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }
}
