package com.cxyxh.mashibing.c_001.T03_otherlock.L10_interview01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 */
public class Answer07 {

    static List<Integer> list = new ArrayList<>();

    public void add(Integer ob) {
        list.add(ob);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        Answer07 answer = new Answer07();
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                answer.add(i);
                System.out.println("add" + i);
                if (answer.size() == 5) {
                    LockSupport.park();
                }
            }
        });

        t2 = new Thread(() -> {
           while (true){
               //如果一直判断的话，
               //另外一边一旦加入，这边就直接能感受到，并做出处理，
               //但是显示效果上不理想
               if (answer.size() == 5){
                   break;
               }
           }
           System.out.println("收到通知结束");
           LockSupport.unpark(t1);
        });

        t1.start();
        t2.start();

    }
}
