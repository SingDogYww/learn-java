package com.cxyxh.c02_frame;

import java.awt.*;
import java.util.Random;

public abstract class Tank {
    protected int x, y;
    protected Dir dir;
    protected int speed;
    protected boolean moving = false;
    protected boolean alive = true;
    public TankFrame tf;
    protected Random random = new Random();
    protected Group group;
    Rectangle rectangle = new Rectangle();
    protected int width, height;
    public AbstractFactory factory;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group, int speed, AbstractFactory factory) {
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
        this.tf = tf;
        this.speed = speed;
        this.factory = factory;
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    public void move() {
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
        if (this.group == Group.BAD && random.nextInt() > 5) {
            this.fire(new DefaultFireStrategy());
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
        boundsCheck();
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 32) y = 32;
        if (this.x > TankFrame.GAME_WIDTH - width) x = TankFrame.GAME_WIDTH - width - 2;
        if (this.y > TankFrame.GAME_HIGHT - height) y = TankFrame.GAME_HIGHT - height - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void die() {
        this.alive = false;
    }

    public abstract void fire();

    public abstract void fire(FireStrategy strategy);

    public abstract void paint(Graphics g);
}
