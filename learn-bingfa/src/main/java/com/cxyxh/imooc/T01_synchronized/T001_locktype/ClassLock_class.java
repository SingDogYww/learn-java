package com.cxyxh.imooc.T01_synchronized.T001_locktype;

public class ClassLock_class implements Runnable{

    static ClassLock_StaticMethod instance1 = new ClassLock_StaticMethod();
    static ClassLock_StaticMethod instance2 = new ClassLock_StaticMethod();

    @Override
    public void run() {
        /**
         * 使用synchronized (*.class) 类似于将代码块提取成一个static方法
         */
        synchronized (ClassLock_class.class){
            System.out.println(Thread.currentThread().getName() + "开始运行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");

        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        thread1.start();
        thread2.start();
    }
}
