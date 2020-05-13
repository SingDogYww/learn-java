package com.cxyxh.c03_tank;

import com.cxyxh.c02_frame.Dir;

import java.awt.*;

public class Bullet {
    private static final int speed = 10;
    private int x, y;
    private Dir dir;
    private static final int width = 100, height = 100;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
        move();
    }

    private void move() {
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


}
