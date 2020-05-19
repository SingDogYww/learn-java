package com.cxyxh.c02_frame;

import java.awt.*;

public abstract class Bullet {
    protected int speed;
    protected int x, y;
    protected Dir dir;
    protected boolean live = true;
    protected TankFrame tf;
    protected Group group;
    protected Rectangle rectangle = new Rectangle();
    public int width, hight;
    public AbstractFactory factory;


    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group, int speed, AbstractFactory factory) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.speed = speed;
        this.factory = factory;
    }

    protected void move() {
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

    protected void die() {
        this.live = false;
    }

    public void collideWith(Tank tank) {
        if (this.group.equals(tank.getGroup())) return;
        if (rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            Explodes explodes = factory.createExplodes(tank);
            this.tf.explodesList.add(explodes);
        }
    }


    public abstract void paint(Graphics g);
}
