package com.example.lovestory.util.test.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SimpleFrame {
    //所有的Swing组件必须由事件分派线程（event dispatch thread）进行配置
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame jFrame = new JFrame();
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLocation(500, 200);
            jFrame.setVisible(true);
            jFrame.setSize(900, 600);

            Image image = new ImageIcon("C:\\cc.jpg").getImage();
            jFrame.setIconImage(image);

            //内容窗格
            MyComponent myComponent = new MyComponent();
            MyTextComponent myTextComponent = new MyTextComponent();
            //jFrame.add(myComponent);

            jFrame.add(myTextComponent);
            //drawPic(new String[]{});

        });
    }

    public static void drawPic(String argv[]) {
        for (float y = (float) 1.5; y > -1.5; y -= 0.1) {
            for (float x = (float) -1.5; x < 1.5; x += 0.05) {
                float a = x * x + y * y - 1;
                if ((a * a * a - x * x * y * y * y) <= 0.0) {

                    System.out.print("*");

                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }

    static class MyComponent extends JComponent {
        @Override
        public void paintComponent(Graphics graphics) {
            Image image = new ImageIcon("C:\\cc.jpg").getImage();
            graphics.drawImage(image, 20, 20, 200, 200, this);
            graphics.drawString("聪聪好可爱", 280, 120);
            graphics.setColor(Color.red);
            graphics.fillRoundRect(480, 100, 100, 100, 100, 100);

        }
    }

    static class MyTextComponent extends JComponent {
        @Override
        public void paintComponent(Graphics graphics) {
            //Line2D  Rectangle2D  Ellipse2D

            Graphics2D graphics2D = (Graphics2D) graphics;
            Rectangle2D.Double r2DDouble = new Rectangle2D.Double(20.0, 20.0, 20.0, 20.0);
            graphics.drawString("聪聪最可爱", 25, 40);
        }
    }

}
