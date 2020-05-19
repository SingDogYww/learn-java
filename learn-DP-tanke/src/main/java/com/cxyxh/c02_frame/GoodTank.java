package com.cxyxh.c02_frame;

import java.awt.*;
import java.util.Random;

public class GoodTank extends Tank{
    private static final int speed = 5;
    public static int width = ResourceMgr.goodTankU.getWidth();
    public static int height = ResourceMgr.goodTankU.getHeight();

    public GoodTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        super(x, y, dir, tf, group, speed, new GoodTankFactory());
        rectangle.width = width;
        rectangle.height = height;
    }

    public void paint(Graphics g) {
        if (!alive) tf.tanks.remove(this);
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankL : ResourceMgr.goodTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankR : ResourceMgr.goodTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankD : ResourceMgr.goodTankD, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankU : ResourceMgr.goodTankU, x, y, null);
                break;
        }
        move();
    }

    public void fire(FireStrategy strategy) {
        strategy.fire(this, factory);
    }

    public void die() {
        this.alive = false;
    }

    public void fire() {
        String defaultFire = PropertyMgr.getString("defaultFire");
        try {
            Class<?> aClass = Class.forName(defaultFire);
            Object o = aClass.newInstance();
            fire((FireStrategy) o);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
