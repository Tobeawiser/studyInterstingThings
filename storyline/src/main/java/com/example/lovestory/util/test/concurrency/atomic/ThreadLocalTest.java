package com.example.lovestory.util.test.concurrency.atomic;

public class ThreadLocalTest {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTest test = new ThreadLocalTest();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set("inheritableValue");
        test.testThreadLocal(inheritableThreadLocal);
        System.out.println(inheritableThreadLocal.get());
    }

    //catch finally 里面都会抛出异常
    //子线程设置值并不会发生改变，因为子线程的inheritableThreadLocal变量初始化是在创建时候发生的
    //改变后也仅仅是在自己当前线程空间中
    public void testThreadLocal(InheritableThreadLocal<String> inheritableThreadLocal) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("thread1");
                threadLocal.set("thread2");
                System.out.println(threadLocal.get());
                threadLocal.set("thread3");
                System.out.println(threadLocal.get());
                System.out.println(inheritableThreadLocal.get());
                inheritableThreadLocal.set("inheritableThreadLocalsssssss");
                System.out.println(inheritableThreadLocal.get());
            }
        }).start();
        inheritableThreadLocal.set("inheritableValueB");
        Thread.sleep(3000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("AnotherThread2");
                System.out.println(threadLocal.get());
                System.out.println(inheritableThreadLocal.get());

            }
        }).start();
        Thread.sleep(3000);
        System.out.println("into main Thread");
        try {
            System.out.println("i am try");
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println("into catch");
            // int i = 1/0;
        } finally {
            //int i = 1/0;
            System.out.println("i am finally");
        }
    }

}
