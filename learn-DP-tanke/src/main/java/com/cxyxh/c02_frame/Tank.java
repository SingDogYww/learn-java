package com.cxyxh.c02_frame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tank {
    private int x, y;
    private Dir dir;
    private static final int speed = 5;
    private boolean moving = false;
    private boolean alive = true;
    private TankFrame tf;
    public static int WIDTH = ResourceMgr.tankL.getWidth();
    public static int HIGHT = ResourceMgr.tankL.getHeight();
    private Random random = new Random();

    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
        this.tf = tf;
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!alive) tf.tanks.remove(this);
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
        }
        move();
    }

    public void move(){
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
        if (tf.tanks.contains(this) && random.nextInt() > 5){
            this.fire();
        }
    }


    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HIGHT / 2 - Bullet.HIGHT / 2;
        tf.bullet.add(new Bullet(bX, bY, this.dir, this.tf, this.group));
    }

    public void die() {
        this.alive = false;
    }
}
