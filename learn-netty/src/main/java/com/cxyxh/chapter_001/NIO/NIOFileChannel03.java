package com.cxyxh.chapter_001.NIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {

    public static void main(String[] args) throws Exception{
        //FIXME 读和写，输入和输出这两个有问题

        File srcFile = new File("F:/netty/netty/NIOFileChannel/file01.txt");
        FileInputStream inputStream = new FileInputStream(srcFile);
        FileChannel channel01 = inputStream.getChannel();
        File targetFile = new File("file02.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
        FileChannel channel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        int read = 0;
        while(read != -1){
            //清空缓冲区，缓冲区的position等就重置了，可以重新读取
            //如果只是反转，数据还没有清完，就会有问题
            //比如，上一次填满了512个字节的数据
            //但是最后一次只有100个，那么他只是将钱100个替换了，后面的还是上次的的数据  
            byteBuffer.clear();
            read = channel01.read(byteBuffer);
            //读写反转
            byteBuffer.flip();
            channel02.write(byteBuffer);
        }
        inputStream.close();
        fileOutputStream.close();
    }
}
