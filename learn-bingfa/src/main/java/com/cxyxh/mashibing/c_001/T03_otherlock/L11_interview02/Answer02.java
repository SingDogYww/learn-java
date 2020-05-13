package com.cxyxh.mashibing.c_001.T03_otherlock.L11_interview02;

import java.util.ArrayList;
import java.util.List;

public class Answer02 {

    List<Integer> list = new ArrayList<>();
    int size = 8;
    int count = 0;
    public synchronized void put(int i){
        while(count == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(i);
        System.out.println("添加一个" + i);
        count++;
        notifyAll();
    }

    public synchronized int get(){
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer remove = list.remove(count-1);
        count--;
        System.out.println("获取一个" + remove);
        notifyAll();
        return remove;
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        Answer02 answer01 = new Answer02();
        List<Thread> producer = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            producer.add(new Thread(() -> answer01.put(j)));
        }

        List<Thread> comsumer = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            comsumer.add(new Thread(answer01::get));
        }
        producer.forEach(Thread::start);
        comsumer.forEach(Thread::start);
    }



}
