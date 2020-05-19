package com.cxyxh.c02_frame;

import java.awt.*;

public class GoodExplodes extends Explodes{
    public static int width = ResourceMgr.bulletL.getWidth();
    public static int height = ResourceMgr.bulletL.getHeight();

    public GoodExplodes(int x, int y, TankFrame tf) {
        super(x, y, tf);
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], this.x, this.y, null);
        if (step >= ResourceMgr.explodes.length){
            tf.explodesList.remove(this);
        }
    }

}
