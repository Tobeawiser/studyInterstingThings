package com.example.lovestory.util.test.draw;

import java.awt.*;

public class DrawGraph extends Frame {
    //graph java.awt包
    //Graphics2D Line2D Rectangle2D Ellipse2D Arc2D
    //Color Stroke


    public DrawGraph(String str) {
        super(str);     //调用父类的构造方法
    }

    // image javax.imageio包
    //  ImageIo BufferedImage ImageReader ImageWriter
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DrawGraph fr = new DrawGraph("First contianer!!");
        fr.setSize(240, 240);    //设置Frame的大小
        fr.setBackground(Color.yellow);      //设置Frame的背景色
        fr.setVisible(true);         //设置Frame为可见

    }

}
