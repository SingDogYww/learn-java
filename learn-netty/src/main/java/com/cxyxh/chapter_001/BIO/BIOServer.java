package com.cxyxh.chapter_001.BIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while(true){
            System.out.println("等待连接");
            //第一个阻塞点
            Socket accept = server.accept();
            System.out.println("服务器接收到了一个连接");
            executorService.execute(() -> {
                handler(accept);
            });
        }
    }

    public static void handler(Socket socket){
        System.out.println("线程的信息：id = " + Thread.currentThread().getId());
        System.out.println("线程的信息：name = " + Thread.currentThread().getName());
        try(InputStream inputStream = socket.getInputStream()){
            byte[] bytes = new byte[1024];
            while(true){
                System.out.println("loading...");
                //第二个阻塞点
                int read = inputStream.read(bytes);
                if (read == -1){
                    System.out.println("连接断开");
                    break;
                }
                System.out.println("输出客户端输出的数据：" + new String(bytes, 0, read));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
