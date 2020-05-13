package com.cxyxh.c02_frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage tankL, tankR, tankD, tankU;
    public static BufferedImage bulletL, bulletR, bulletD, bulletU;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getResource("/img/tank/tankL.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getResource("/img/tank/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getResource("/img/tank/tankD.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getResource("/img/tank/tankU.gif"));

            bulletL = ImageIO.read(ResourceMgr.class.getResource("/img/bullet/bulletL.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getResource("/img/bullet/bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getResource("/img/bullet/bulletD.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getResource("/img/bullet/bulletU.gif"));

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getResource("/img/boom/e"+ (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
