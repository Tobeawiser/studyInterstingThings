package com.example.lovestory.util.test.srializable;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.entity.MqUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSrializable {

    public static <T> void objectSrializable(T t, File files) throws IOException, ClassNotFoundException {
        if (t == null) {
            throw new NullPointerException("param is null");
        }

        FileOutputStream fileOutputStream = new FileOutputStream(files);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(t);

        System.out.println("写入完毕");

        //读取
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(files));
        T obj = (T) inputStream.readObject();
        System.out.println("读取完毕");
        System.out.println(JSON.toJSONString(obj));
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //        MqUser mqUser = new MqUser(1, "18", "cong" ,  s );
        MqUser mqUser = new MqUser();
        mqUser.setAge("2");

        //输出流  默认追加模式为false
        //获取路径
        String property = System.getProperty("user.dir");
        //
        String path = property + "\\src\\main\\resources\\" + DateUtil.today();
        String uuid = UUID.fastUUID().toString() + ".dat";

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(path + "\\" + uuid);
        file.createNewFile();

        objectSrializable(mqUser, file);

    }
    //C:\Users\liaochengyuan\IdeaProjects\love-story\src\main\resources\2023-03-05\75547777-9411-4946-a63f-9b5ca8590f11.dat
    //C:\Users\liaochengyuan\IdeaProjects\love-story\src\main\resources\2023-03-05
}
