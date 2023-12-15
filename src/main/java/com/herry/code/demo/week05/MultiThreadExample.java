package com.herry.code.demo.week05;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadExample {
    private static final int NUM_THREADS = 100;
    private static AtomicInteger counter = new AtomicInteger();

    private static int a = 0;


    public static void main(String[] args) {
        // 创建并启动多个线程
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new CounterRunnable());
            thread.start();
        }
    }

    static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            // 对共享计数器进行增加操作
//            int newValue = counter.incrementAndGet();
            a ++;
            // 打印当前线程名称和增加后的值
//            System.out.println("Thread " + Thread.currentThread().getName() + ": Counter value = " + newValue);
            System.out.println("Thread " + Thread.currentThread().getName() + ": Counter value = " + a);

        }

    }
}
