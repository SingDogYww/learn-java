package com.cxyxh.mashibing.c_001.T03_otherlock.L11_interview02;

import java.util.ArrayList;
import java.util.List;

/**
 * 错误写法，无效
 */
public class Answer01 {

    List<Integer> list = new ArrayList<>();
    int size = 8;
    int count = 0;
    public synchronized void put(int i){
        while(count < size) {
            list.add(i);
            System.out.println("添加一个" + i);
            count++;
        }
        notifyAll();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized int get(){
        while (count != 0) {
            Integer remove = list.remove(count-1);
            count--;
            System.out.println("获取一个" + remove);
            return remove;
        }
        try {
            notifyAll();
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        Answer01 answer01 = new Answer01();
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
