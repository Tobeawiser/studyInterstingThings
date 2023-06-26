package com.example.lovestory.learn.producerconsumer;

public class Test {

    public static void main(String[] args) {
        //产品
        int[] product = {0};
        Object object = new Object();
        //生产
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            synchronized (object) {
                                if (product[0] > 0) {
                                    //进行消费
                                    product[0] = product[0] - 1;
                                    System.out.println("消费完成， product[0] = " + product[0]);
                                    //唤醒生产者
                                    object.notify();
                                } else {
                                    try {
                                        System.out.println("消费者进行等待，product[0] =" + product[0]);
                                        object.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                }
        ).start();
        //生产
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            synchronized (object) {
                                if (product[0] <= 0) {
                                    //进行生产
                                    product[0] = product[0] + 1;
                                    System.out.println("生产完成， product[0] = " + product[0]);
                                    //唤醒消费者
                                    object.notify();
                                } else {
                                    try {
                                        System.out.println("生产者进行等待，product[0] =" + product[0]);
                                        object.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }

                    }
                }
        ).start();

        System.out.println("h");
    }

}
