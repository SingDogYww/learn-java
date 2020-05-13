package com.cxyxh.c01_test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

    @Test
    public void testImage() throws IOException {
        String src = "/img/tank-icon.png";
        BufferedImage image = ImageIO.read(getClass().getResource(src));
        Assertions.assertNotNull(image);
    }

}
