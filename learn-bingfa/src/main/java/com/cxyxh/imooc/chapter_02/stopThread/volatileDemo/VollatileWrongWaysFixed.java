package com.cxyxh.imooc.chapter_02.stopThread.volatileDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class VollatileWrongWaysFixed {

    public static void main(String[] args) throws InterruptedException {
        VollatileWrongWaysFixed thiss = new VollatileWrongWaysFixed();
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        VollatileWrongWaysFixed.Producer producer = thiss.new Producer(blockingQueue);
        Thread thread = new Thread(producer);
        thread.start();

        VollatileWrongWaysFixed.Consumer consumer = thiss.new Consumer(blockingQueue);
        while(consumer.isGoule()){
            System.out.println(consumer.queue.take() + "被取出来了");
            Thread.sleep(500);
        }
        System.out.println("消费者吃够了，不想再吃了");
        //FIXME 在这种阻塞的情况下，被中断，会抛出异常，然后停止线程
        thread.interrupt();
    }
    class Producer implements Runnable {
        BlockingQueue<Integer> queue;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num < 10000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {

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
}


