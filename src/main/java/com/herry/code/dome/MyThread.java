package com.herry.code.dome;

public class MyThread extends Thread{
    @Override
    //线程体,启动线程时会运行run()方法中的代码
    public void run() {
        //输出100以内的偶数
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":\t"+i);
            }
        }
    }

    public static void main(String[] args) {
        //创建一个Thread类的子类对象
        MyThread t1 = new MyThread();
        //通过此对象调用start()启动一个线程
        t1.start();
        //注意:已经启动过一次的线程无法再次启动
        //再创建一个线程
        MyThread t2 = new MyThread();
        t2.start();

        //另一种调用方法,此方法并没有给对象命名
        new MyThread().start();

        System.out.println("主线程");
    }
}