package com.cxyxh.c03_tank;

import com.cxyxh.c02_frame.Dir;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tank {
    private int x, y;
    private Dir dir = Dir.UP;
    private static final int speed = 10;
    private boolean moving = false;
    boolean shoot = false;
    List<Bullet> bullets = new ArrayList<>();
    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
        if (shoot) {
            Bullet bullet = new Bullet(200, 200, dir);
            bullets.add(bullet);
            shoot = false;
        }
        List<Bullet> filter = bullets.stream().filter(o1 -> (o1.getX() < 800 && o1.getX() > 0) && (o1.getY() < 600 && o1.getY() > 0)).collect(Collectors.toList());
        bullets = filter;
        filter.forEach(o1 -> o1.paint(g));
        System.out.println(filter.size());
        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            case UP:
                y -= speed;
                break;
        }
    }

    /**
     * 键盘监听处理类
     */
    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        /**
         * 当某个键被按下
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_SPACE:
                    shoot = true;
                    System.out.println("按下空格");
                    break;
            }
            //重新设置方向
            setTankMainDir();
        }

        private void setTankMainDir() {
            if (bL) dir = Dir.LEFT;
            if (bR) dir = Dir.RIGHT;
            if (bD) dir = Dir.DOWN;
            if (bU) dir = Dir.UP;

            if (!bD && !bL && !bR && !bU){
                moving = false;
            }else{
                moving = true;
            }
        }

        /**
         * 当某个键释放的时候
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
            }
            //重新设置方向
            setTankMainDir();
        }


    }

}
