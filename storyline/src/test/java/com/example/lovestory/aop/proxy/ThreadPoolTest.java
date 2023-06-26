package com.example.lovestory.aop.proxy;

import com.example.lovestory.service.RunneableTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ThreadPoolTest {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    public static void main(String[] args) {
        int core = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                core,
                20,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(500),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        int corePoolSize = threadPoolExecutor.getCorePoolSize();
        System.out.println("核心綫程數為：" + corePoolSize);
        for (int i = 0; i < 300; i++) {
            System.out.println("i=" + i);
            RunneableTask runneableTask = new RunneableTask(i);
            threadPoolExecutor.execute(runneableTask);
            if (i == 150) {
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("出現異常");
                        int i = 1 / 0;
                    }
                });
            }
        }
        //threadPoolExecutor.shutdown();

    }

    @Test
    public void test() {
        for (int i = 0; i < 300; i++) {
            RunneableTask runneableTask = new RunneableTask(9);
            threadPoolExecutor.execute(runneableTask);
        }
        try {
            threadPoolExecutor.shutdown();
            while (!threadPoolExecutor.awaitTermination(2, TimeUnit.SECONDS)) {
                boolean b = threadPoolExecutor.awaitTermination(2, TimeUnit.SECONDS);
                System.out.println(b);
            }
            System.out.println("运行结束");
        } catch (InterruptedException e) {

        }

    }

    @Test
    public void testException() {
        int corePoolSize = threadPoolExecutor.getCorePoolSize();
        System.out.println("核心綫程數為：" + corePoolSize);
        for (int i = 0; i < 300; i++) {
            RunneableTask runneableTask = new RunneableTask(i);
            threadPoolExecutor.execute(runneableTask);
        }

    }
}
