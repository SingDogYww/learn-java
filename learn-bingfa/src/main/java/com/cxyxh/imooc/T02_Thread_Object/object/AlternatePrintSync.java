package com.cxyxh.imooc.T02_Thread_Object.object;

/**
 * 面试题：交替打印奇偶数，范围0-100
 * 解决方案：使用synchronized
 */
public class AlternatePrintSync {
    private static int count;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        //单从结果看是没有问题的，但是问题在于可能会做许多无效的判断是否为奇数或者偶数，因为线程可能会连续多次获取到锁
        new Thread(()->{
            while (count < 100){
                synchronized (lock){
                    if ((count & 1) == 0){
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "偶数线程").start();

        new Thread(()->{
            while (count < 100){
                synchronized (lock){
                    if ((count & 1) == 1){
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "奇数线程").start();
    }
}
