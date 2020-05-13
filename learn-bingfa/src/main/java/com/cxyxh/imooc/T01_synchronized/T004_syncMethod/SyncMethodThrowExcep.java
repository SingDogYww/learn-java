package com.cxyxh.imooc.T01_synchronized.T004_syncMethod;

public class SyncMethodThrowExcep {

    public synchronized void method(){
        for (int i = 0; i < 2000; i++) {
            System.out.println(Thread.currentThread().getName() + "打印" + i);
            if (i == 100){
                //抛出异常之后会释放锁
                int j = 1/ 0;
            }
        }
    }

    public synchronized void method2(){
        for (int i = 0; i < 2000; i++) {
            System.out.println(Thread.currentThread().getName() + "打印" + i);
            if (i == 100){
                //抛出异常之后会释放锁，如果被捕获的话，锁不会释放
                try {
                    int j = 1 / 0;
                }catch (RuntimeException e){
                }
            }
        }
    }




    public static void main(String[] args) {
        SyncMethodThrowExcep testExcep = new SyncMethodThrowExcep();
//        Thread thread1 = new Thread(testExcep::method);
//        Thread thread2 = new Thread(testExcep::method);
//        thread1.start();
//        thread2.start();

        Thread thread3 = new Thread(testExcep::method2);
        Thread thread4 = new Thread(testExcep::method2);
        thread3.start();
        thread4.start();
    }






}
