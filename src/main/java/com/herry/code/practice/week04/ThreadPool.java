package com.herry.code.practice.week04;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟线程池
 * @author herry
 */
public class ThreadPool {
    /**
     * 核心线程数
     */
    private final int corePoolSize;

    /**
     * 最大线程数
     */
    private final int maximumPoolSize;

    /**
     * 线程空闲时间
     */
    private final long keepAliveTime;

    /**
     * 时间单位
     */
    private final TimeUnit unit;

    /**
     * 工作队列
     */
    private final BlockingQueue<Runnable> workQueue;

    /**
     * 线程工厂
     */
    private final ThreadFactory threadFactory;

    /**
     * 任务拒绝策略
     */
    private final RejectedExecutionHandler handler;

    /**
     * 当前活跃的线程数量
     */
    private final AtomicInteger activeCount = new AtomicInteger(0);

    /**
     * 状态：0：运行中，1：关闭中，2：已关闭
     */
    private volatile int status = 0;

    /**
     * 锁对象
     */
    private final Object lock = new Object();

    public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                      BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

    /**
     * 提交任务
     */
    public void submit(Runnable task) {
        synchronized (lock) {
            // 如果线程池已经关闭，则抛出异常
            if (status >= 2) {
                throw new IllegalStateException("线程池已关闭");
            }
            // 如果当前活跃的线程数量小于核心线程数，则直接执行任务
            if (activeCount.get() < corePoolSize) {
                executeTask(task);
            } else {
                // 否则，将任务加入工作队列
                boolean offerSuccess = workQueue.offer(task);
                if (!offerSuccess) {
                    // 如果工作队列已满，且当前活跃的线程数量小于最大线程数，则创建新线程执行任务
                    if (activeCount.get() < maximumPoolSize) {
                        executeTask(task);
                    }
                }
            }
        }
    }

    /**
     * 执行任务
     */
    private void executeTask(Runnable task) {
        // 活跃线程数加1
        activeCount.incrementAndGet();
        // 创建新线程并启动
        threadFactory.newThread(new Worker(task)).start();
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        synchronized (lock) {
            if (status == 1) {
                // 已经在关闭过程中了，直接返回
                return;
            }
            // 线程池状态置为“关闭中”
            status = 1;
            // 如果工作队列为空，直接关闭
            if (workQueue.isEmpty()) {
                // 线程池状态置为“已关闭”
                status = 2;
                // 唤醒所有等待的线程
                lock.notifyAll();
            }
        }
    }

    /**
     * 获取线程池当前状态
     */
    public String getStatus() {
        switch (status) {
            case 0:
                return "运行中";
            case 1:
                return "关闭中";
            case 2:
                return "已关闭";
            default:
                return "未知";
        }
    }

    /**
     * 工作线程
     */
    private class Worker implements Runnable {
        private final Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            try {
                // 执行任务
                task.run();
            } finally {
                // 活跃线程数减1
                activeCount.decrementAndGet();
                // 调度下一个任务
                scheduleNextTask();
            }
        }
    }

    /**
     * 调度下一个任务
     */
    private void scheduleNextTask() {
        synchronized (lock) {
            if (!workQueue.isEmpty() && activeCount.get() < maximumPoolSize) {
                // 如果工作队列不为空，且当前活跃的线程数量小于最大线程数，则取出队首任务执行
                Runnable nextTask = workQueue.poll();
                executeTask(nextTask);
            } else if (status == 1 && workQueue.isEmpty()) {
                // 如果线程池正在关闭，并且工作队列已经为空，说明所有任务都已经完成，将线程池状态置为“已关闭”
                status = 2;
                // 唤醒所有等待的线程
                lock.notifyAll();
            }
        }
    }

}
