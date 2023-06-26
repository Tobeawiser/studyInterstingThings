package com.example.lovestory.service;

public class RunneableTask implements Runnable {
    private int count = 0;

    public RunneableTask(int count) {
        this.count = count;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName());
        System.out.println("运行完成");
        if (count == 150) {
            System.out.println("出現異常");
            int i = 1 / 0;
        }

    }
}
