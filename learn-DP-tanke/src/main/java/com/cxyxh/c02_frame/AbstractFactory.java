package com.cxyxh.c02_frame;

public abstract class AbstractFactory {
    abstract Bullet createBullet(Tank tank, Dir dir);
    abstract Tank createTank(int x, int y, Dir dir, TankFrame tf);
    abstract Explodes createExplodes(Tank tank);


}
