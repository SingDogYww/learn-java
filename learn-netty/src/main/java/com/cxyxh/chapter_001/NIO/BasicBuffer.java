package com.cxyxh.chapter_001.NIO;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        //创建大小为5的缓冲区，用于存放int类型的数据
        IntBuffer buffer = IntBuffer.allocate(5);
        //往缓冲区存放数据
        for (int i = 0; i < buffer.capacity() - 1; i++){
            buffer.put(i * 2 + 10);
        }

        //Buffer中的capacity表示最大的数量


        //往buffer中去数据

        //首先需要读写转换一下，转换之后，position有原来的位置变成初始位置0，limit由原来的和容量一样的值，
        //变成前面往缓冲区中添加的数量
        //limit = position;
        //position = 0;
        //mark = -1;
        //return this;
        buffer.flip();
        //判断buffer中是否还剩有数据
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }






    }
}
