package com.example.lovestory.learn.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NioBuffer {
    public static void main(String[] args) throws Exception {
        IntBuffer intBuffer = IntBuffer.allocate(5);

        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.put(3);
        intBuffer.put(4);

        int capacity = intBuffer.capacity();
        System.out.println(capacity);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            int i1 = intBuffer.get(i);
            System.out.println(i1);
        }

        //chanell buffer 一起用
        String filePath = "C:\\Users\\liaochengyuan\\IdeaProjects\\love-story\\storyline\\src\\main\\java\\com\\example\\lovestory\\learn\\nio\\a.txt";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        FileChannel outputChannel = fileOutputStream.getChannel();
        FileChannel inputChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(60);


        byteBuffer.put("hellowhelodeo".getBytes(StandardCharsets.UTF_8));
        //关键性的一步  需要将其转换为可读模式
        byteBuffer.flip();
        outputChannel.write(byteBuffer);

        //读取
        ByteBuffer readBuffer = ByteBuffer.allocate(60);
        int read = inputChannel.read(readBuffer);
        //变为可读
        byte[] array = readBuffer.array();
        String string = new String(array);
        System.out.println(string);
        //关闭流


    }
}
