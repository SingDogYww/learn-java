package com.cxyxh.c02_frame.cor;

import com.cxyxh.c02_frame.Bullet;
import com.cxyxh.c02_frame.GameObject;
import com.cxyxh.c02_frame.Tank;

import java.awt.*;

/**
 * 负责校验坦克和子弹的相撞
 */
public class TankTankCollider implements Collider {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;

            Rectangle rectangle = t1.getRectangle();
            Rectangle rectangle1 = t2.getRectangle();
            if (rectangle.intersects(rectangle1)){
                t1.move();
                t2.move();
            }



        }

    }
}
