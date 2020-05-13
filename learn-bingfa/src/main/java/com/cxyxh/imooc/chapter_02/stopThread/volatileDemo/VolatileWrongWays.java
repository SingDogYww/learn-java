package com.cxyxh.imooc.chapter_02.stopThread.volatileDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class VolatileWrongWays {


    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        Producer producer = new Producer(blockingQueue);
        Thread thread = new Thread(producer);
        thread.start();

       Consumer consumer = new Consumer(blockingQueue);
       while(consumer.isGoule()){
           System.out.println(consumer.queue.take() + "被取出来了");
           Thread.sleep(500);
       }
       System.out.println("消费者吃够了，不想再吃了");
       producer.cancled = true;
    }

}


class Producer implements Runnable {
    BlockingQueue<Integer> queue;

    public volatile boolean cancled = false;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 10000 && !cancled) {
                if (num % 100 == 0) {
                    //FIXME 在这种情况下，生产者生产较快，就会阻塞在这里，并不会去判断是否已经需要中断了
                    queue.put(num);
                    System.out.println(num + "被放入生产队列中了");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer {
    BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public boolean isGoule(){
        if (Math.random() < 0.85){
            return true;
        }
        return false;
    }
}