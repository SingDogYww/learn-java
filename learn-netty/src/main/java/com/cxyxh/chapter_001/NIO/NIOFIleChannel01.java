package com.cxyxh.chapter_001.NIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFIleChannel01 {
    public static void main(String[] args) throws IOException {
        //创建流
        FileOutputStream fileOutputStream = new FileOutputStream("F:/netty/netty/NIOFileChannel/file01.txt");
        //获取Channel
        FileChannel channel = fileOutputStream.getChannel();
        //创建消息
        String message = "毛不易 《牧马城市》" +
                "\n" +
                "游历在大街和楼房  心中是骏马和猎场 最了不起的脆弱迷惘\n" +
                "\n" +
                "不过就这样  天外有天有无常  山外有山有他乡\n" +
                "\n" +
                "跌了撞了 心还是回老地方\n" +
                "\n" +
                "游离于城市的痛痒  错过了心爱的姑娘  宣告世界的那个理想\n" +
                "\n" +
                "已不知去向  为所欲为是轻狂  防不胜防是悲伤\n" +
                "\n" +
                "后来才把成熟当偏方  当所有想的说的要的爱的  都挤在心脏\n" +
                "\n" +
                "行李箱里装不下我 想去的远方\n" +
                "\n" +
                "这来的去的给的欠的 算一种褒奖  风吹草低见惆怅 抬头至少还有光\n" +
                "\n" +
                "游历在大街和楼房  心中是骏马和猎场\n" +
                "\n" +
                "最了不起的脆弱迷惘  不过就这样\n" +
                "\n" +
                "天外有天有无常  山外有山有他乡\n" +
                "\n" +
                "跌了撞了 心还是回老地方  游离于城市的痛痒  错过了心爱的姑娘\n" +
                "\n" +
                "宣告世界的那个理想  已不知去向\n" +
                "\n" +
                "为所欲为是轻狂  防不胜防是悲伤  后来才把成熟当偏方\n" +
                "\n" +
                "当所有想的说的要的爱的  都挤在心脏\n" +
                "\n" +
                "行李箱里装不下我 想去的远方  这来的去的给的欠的算一种褒奖\n" +
                "\n" +
                "风吹草低见惆怅  抬头至少还有光  把烦恼痛了吞了认了算了\n" +
                "\n" +
                "不对别人讲  谁还没有辜负几段 昂贵的时光\n" +
                "\n" +
                "若男孩笑了哭了累了  说要去流浪  留下大人的模样\n" +
                "\n" +
                "看岁月剑拔弩张  总会有个人成为你的远方";

        //FIXME 注意的是，读和写的方向，还有输入和输出的对应方向
        //FIXME 输出对应的是写针对于某个目标对象来说是入


        //创建ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(102400);
        //想buffer中写入数据
        byteBuffer.put(message.getBytes());
        //准备写入Channel中，先反转
        byteBuffer.flip();
        //channel中写入数据
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
