package com.cxyxh.c03_tank;


import com.cxyxh.c02_frame.Dir;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * 创建一个窗口
 */
public class TankFrame extends Frame {
    Tank tank1 = new Tank(200, 200, Dir.DOWN);
    Bullet bullet = new Bullet(500, 500, Dir.DOWN);
    public TankFrame() {
        //设置宽度
        setSize(800, 600);
        //设置窗口不能变换大小
        setResizable(false);
        //设置标题
        setTitle("坦克大战");
        //设置图标
        String src = "/img/tank-icon.png";
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResource(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(image);
        //设置窗口事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //窗口关闭事件
                System.exit(0);
            }
        });
        //添加键盘处理类
        addKeyListener(tank1.new MyKeyListener());
        //设置可见，需要最后设置
        setVisible(true);
    }

    /**
     * paint方法在每次窗口重新绘制时会调用
     * 也就是在窗口最小化最大化的时候会调用
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        tank1.paint(g);
//        bullet.paint(g);
    }
}




