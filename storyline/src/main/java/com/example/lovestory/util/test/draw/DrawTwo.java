package com.example.lovestory.util.test.draw;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawTwo {
    public static void main(String[] args) {
        Frame f = new Frame("my awt");
        f.setLayout(new FlowLayout());
        f.setVisible(true);
        f.setSize(300, 200);
        f.setLocation(700, 400);


        Button b = new Button("我是一个按钮");//定义一个按钮,名称为"我是一个按钮"
        f.add(b);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("我关!");
                System.exit(0); //点击右上角关闭按钮,退出虚拟机
            }

            public void windowActivated(WindowEvent e1) {
                System.out.println("activated"); //每次窗口被激活,打印"activated"
            }

            public void windowOpened(WindowEvent e2) {
                System.out.println("我活啦!"); //每次打开窗口,会输出"我活啦"
            }
        });
        System.out.println("hello world!");
    }
}

class myWindow extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.out.println("window closing");
        e.getWindow();
        System.out.println(e.toString());
        System.out.println(e.getWindow());
        System.out.println(e.getSource());
        System.exit(0);
    }
}
