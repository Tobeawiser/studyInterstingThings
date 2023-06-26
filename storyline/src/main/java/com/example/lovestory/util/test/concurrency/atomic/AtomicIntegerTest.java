package com.example.lovestory.util.test.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;


public class AtomicIntegerTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerTest test = new AtomicIntegerTest();
        test.testAll();
    }

    public void testAll() throws InterruptedException {
        final AtomicInteger value = new AtomicInteger(10);

        boolean b = value.compareAndSet(1, 2);
        System.out.println(b);
        int i1 = value.get();
        System.out.println(i1);
        boolean b1 = value.compareAndSet(10, 3);
        System.out.println(b1);
        int i2 = value.get();
        System.out.println(i2);
        value.set(0);

        int i3 = value.incrementAndGet();
        System.out.println(i3);
        int andAdd = value.getAndAdd(2);
        System.out.println(andAdd);
        int andSet = value.getAndSet(5);
        System.out.println(andSet);
        int i4 = value.get();
        System.out.println(i4);
        //
        final int threadSize = 10;
        Thread[] ts = new Thread[threadSize];
        for (int i = 0; i < threadSize; i++) {
            ts[i] = new Thread() {
                public void run() {
                    int i5 = value.incrementAndGet();
                    System.out.println(i5);
                    String name = Thread.currentThread().getName();
                    System.out.println(name);
                }
            };
        }
        //
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            t.join();
        }
        //
        value.get();
    }

}

