package com.cxyxh.c02_frame;

/**
 * 开火策略1：正常发放
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank, AbstractFactory factory) {
        Bullet bullet = factory.createBullet(tank, tank.getDir());
        tank.tf.bullet.add(bullet);
    }
}
