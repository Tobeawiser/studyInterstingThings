package net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        while (true) {
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];

            InputStream inputStream = socket.getInputStream();

            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println("输出客户端发送数据");
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
