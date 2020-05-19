package com.cxyxh.c02_frame;

/**
 * 开火策略2：四个方向都开火
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank, AbstractFactory factory) {

        Dir[] values = Dir.values();
        for (int i = 0; i < values.length; i++) {
            Bullet bullet = factory.createBullet(tank, values[i]);
            tank.tf.bullet.add(bullet);
        }
    }
}
