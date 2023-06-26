package com.example.lovestory.util.test.concurrency.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ThreadSync {

    public static void main(String[] args) throws InterruptedException {
        //countDownLatch();
        cyclicbarrier();
    }

    private static void countDownLatch() throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(5);
        for (int i = 0; i <= 3; i++) {
            System.out.println("downLatch.getCount" + downLatch.getCount());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "减去1");
                    downLatch.countDown();
                }
            }).run();
        }
        System.out.println("等待中");
        downLatch.await();
        System.out.println("downLatch为0，等待结束");
    }

    private static void cyclicbarrier() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        //线程内部多个线程同时到达某一节点然后继续运行
        //需要写好一个完整的Thread，然后再让其跑起来，个数需要和CyclicBarrier相同
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000 * 1);
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "我进来了，第i轮:" + (finalI % 3));

                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {

                    }
                    System.out.println("等待结束" + Thread.currentThread().getName());
                }
            });
            thread.start();
        }
    }
}
