package com.cxyxh.imooc.T01_synchronized.T001_locktype;

/**
 * 对象锁中的 方法锁
 * 类似于代码块
 */
public class ObjectLock_ObjectMethod {
    private static class codeBlock implements Runnable{

        @Override
        public void run() {
            method();
        }

        public /*synchronized*/ void method(){
            System.out.println(Thread.currentThread().getName() + "开始运行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        codeBlock codeBlock = new codeBlock();
        Thread t1 = new Thread(codeBlock);
        Thread t2 = new Thread(codeBlock);
        t1.start();
        t2.start();
    }



}
