package com.cxyxh.c02_frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    private static final ResourceMgr instance = new ResourceMgr();
    private ResourceMgr(){}
    public static ResourceMgr getInstance() {
        return instance;
    }
    public static BufferedImage goodTankL, goodTankR, goodTankD, goodTankU;
    public static BufferedImage badTankL, badTankR, badTankD, badTankU;
    public static BufferedImage bulletL, bulletR, bulletD, bulletU;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getResource("/img/tank/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            badTankU = ImageIO.read(ResourceMgr.class.getResource("/img/tank/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            bulletU = ImageIO.read(ResourceMgr.class.getResource("/img/bullet/GoodBulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getResource("/img/boom/e"+ (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
