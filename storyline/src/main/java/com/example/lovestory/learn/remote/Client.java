package com.example.lovestory.learn.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        try {
            // 查找远程服务
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            HelloService service = (HelloService) registry.lookup("HelloService");

            // 调用远程方法
            String result = service.sayHello("World");
            System.out.println(result);  // 输出: Hello, World!
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
