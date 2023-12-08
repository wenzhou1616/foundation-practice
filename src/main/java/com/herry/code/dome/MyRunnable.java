package com.herry.code.dome;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        // 打印偶数
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":\t" + i);
            }
        }
    }

    public static void main(String[] args) {
        //创建实现类的对象
        MyRunnable myRunnable = new MyRunnable();
        //创建Thread类的对象,并将实现类的对象当做参数传入构造器
        Thread t1 = new Thread(myRunnable);
        //使用Thread类的对象去调用Thread类的start()方法:①启动了线程 ②Thread中的run()调用了Runnable中的run()
        t1.start();

        //在创建一个线程时，只需要new一个Thread类就可,不需要new实现类
        Thread t2 = new Thread(myRunnable);
        t2.start();
    }
}