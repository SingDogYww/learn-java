package com.cxyxh.imooc.T02_Thread_Object.object;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerModel {
    public static void main(String[] args) {
        Container container = new Container();
        Producer producer = new Producer(container);
        Consumer consumer = new Consumer(container);
        new Thread(producer).start();
        new Thread(consumer).start();
    }


    static class Container {
        private int maxSize;
        private List<String> container;

        public Container() {
            maxSize = 10;
            container = new ArrayList<>();
        }

        public synchronized void add() {
            while (container.size() == maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            container.add("产品");
            System.out.println("生产了一个产品，还剩下" + container.size() + "个产品");
            notify();
        }


        public synchronized void take(){
            while (container.size() == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String remove = container.remove(0);
            System.out.println("消费了一个" + remove + "，还剩下" + container.size() + "个产品");
            notify();
        }

    }

    static class Producer implements Runnable{
        private Container container;

        public Producer(Container container) {
            this.container = container;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                container.add();
            }
        }
    }

    static class Consumer implements Runnable{
        private Container container;

        public Consumer(Container container) {
            this.container = container;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                container.take();
            }
        }
    }

}
