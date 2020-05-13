package com.cxyxh.chapter_001.NIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\localhost\\Pictures\\微信图片_20190908200638.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("极限男人帮.jpg");

        FileChannel srcChannel = fileInputStream.getChannel();
        FileChannel targetChannel = fileOutputStream.getChannel();

        targetChannel.transferFrom(srcChannel, 0, srcChannel.size());

        fileInputStream.close();
        fileOutputStream.close();


    }
}

