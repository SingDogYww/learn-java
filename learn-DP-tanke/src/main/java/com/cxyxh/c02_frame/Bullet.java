package com.cxyxh.c02_frame;

import java.awt.*;

public class Bullet {
    private static final int speed = 10;
    private int x, y;
    private Dir dir;
    private static final int width = 50, height = 50;
    private boolean live = true;
    private TankFrame tf;
    public static int WIDTH = ResourceMgr.bulletL.getWidth();
    public static int HIGHT = ResourceMgr.bulletL.getHeight();
    private Group group;

    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
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

    public void paint(Graphics g) {
        if (!live){
            tf.bullet.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
        }
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HIGHT) live = false;
    }


    public void collideWith(Tank tank) {
        if (this.group.equals(tank.getGroup())) return;
        //TODO 每次都会new对象，会让垃圾回收器时不时的回收一下
        Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HIGHT);
        Rectangle tankRect = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HIGHT);
        if (bulletRect.intersects(tankRect)){
            tank.die();
            this.die();
            this.tf.explodesList.add(new Explodes(x, y, this.tf));
        }
    }

    private void die() {
        this.live = false;
    }
}
