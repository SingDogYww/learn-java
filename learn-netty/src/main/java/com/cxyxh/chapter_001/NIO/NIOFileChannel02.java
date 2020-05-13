package com.cxyxh.chapter_001.NIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {

    public static void main(String[] args) throws IOException {
        File file = new File("F:/netty/netty/NIOFileChannel/file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        channel.read(byteBuffer);
        //array()返回Buffer中存储的数组
        String str = new String(byteBuffer.array());
        System.out.println(str);
        fileInputStream.close();
    }

}
