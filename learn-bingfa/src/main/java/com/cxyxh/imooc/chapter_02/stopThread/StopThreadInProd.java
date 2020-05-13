package com.cxyxh.imooc.chapter_02.stopThread;

public class StopThreadInProd implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                System.out.println("学习使我快乐");
                throwException();
            } catch (Exception e) {
//                不加这句的话，会一直处于循环中
//                Thread.currentThread().interrupt();
                System.out.println("一系列操作");
                e.printStackTrace();
            }
        }
    }

    private void throwException() throws Exception{
        Thread.sleep(3000);
        throw new Exception();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
