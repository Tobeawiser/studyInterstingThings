package com.example.lovestory.util.test.jframe;

import javax.swing.*;
import java.awt.*;

public class TankWar extends JFrame {
    public TankWar(String title) {
        super(title);
        this.setSize(600, 500);
        this.setLocationRelativeTo(null); //设置窗口的位置屏幕中间
        this.setResizable(false); //设置为不可改变大小
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //设置关闭事件

        MyJPanel myJPanel = new MyJPanel(); //创建一个MyJPanel实例对象myJPanel
        myJPanel.setBackground(Color.blue);//设置myJPanel的背景颜色
        this.add(myJPanel);
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new TankWar("坦克大战");
    }

    /*内部继承JPanel类*/
    class MyJPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.red);
            g.fillOval(200, 100, 300, 300);
            g.setColor(c);
        }
    }
}
