package com.cxyxh.c02_frame.cor;

import com.cxyxh.c02_frame.Bullet;
import com.cxyxh.c02_frame.GameObject;
import com.cxyxh.c02_frame.Tank;

/**
 * 负责校验坦克和子弹的相撞
 */
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank)o2;
            if (b.collideWith(t)){
                return true;
            }
        }
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            collide(o2, o1);
        }
        return false;
    }
}
