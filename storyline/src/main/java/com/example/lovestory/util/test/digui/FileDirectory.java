package com.example.lovestory.util.test.digui;

import java.io.File;

public class FileDirectory {

    public static void main(String[] args) {
        String fileName = "C:\\";
        File file = new File(fileName);
        if (file.exists()) {
            printAllChildrenDirectory(file);
        }
    }

    private static void printAllChildrenDirectory(File file) {
        //这个简单些，都不用返回结果
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                //如果遇到文件夹，再次调用此方法
                if (f.isDirectory()) {
                    printAllChildrenDirectory(f);
                } else {
                    //如果是文件，则打印出名字
                    System.out.println("文件名：" + f.getName() + "父目录为：" + file.getName());
                }


            }
        }


    }
}
