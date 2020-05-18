package com.cxyxh.c02_frame;

/**
 * 开火策略1：正常发放
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HIGHT / 2 - Bullet.HIGHT / 2;
        tank.tf.bullet.add(new Bullet(bX, bY, tank.getDir(), tank.tf, tank.getGroup()));
    }
}