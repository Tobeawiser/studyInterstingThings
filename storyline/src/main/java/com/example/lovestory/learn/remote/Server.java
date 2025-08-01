package com.example.lovestory.learn.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            // 创建服务实例
            HelloService service = new HelloServiceImpl();

            // 注册服务到RMI注册表
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("HelloService", service);

            System.out.println("服务已启动...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
