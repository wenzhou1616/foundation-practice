package com.herry.code.practice.week05;

import lombok.extern.slf4j.Slf4j;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


/**
 * 模拟HTTP服务器
 * @author herry
 */
@Slf4j
public class HttpServer {
    /**
     * 用于启动 HTTP 服务器的线程池
     */
    private static ExecutorService bootstrapExecutor = Executors.newSingleThreadExecutor();

    /**
     * 用于处理来自客户端的 HTTP 请求的线程池
     */
    private static ExecutorService taskExecutor;

    /**
     * 服务器监听端口号
     */
    private static final int PORT = 9000;

    /**
     * 启动 HTTP 服务器
     */
    static void startHttpServer() {
        // 获取处理器可用核心数，用于设置线程池大小
        int nThreads = Runtime.getRuntime().availableProcessors();
        // 初始化线程池，设置线程池大小，队列大小和丢弃策略
        taskExecutor = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100), new ThreadPoolExecutor.DiscardPolicy());

        // 循环尝试启动服务器，如果启动失败，则等待10秒后重试
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                bootstrapExecutor.submit(new ServerThread(serverSocket));
                break;
            } catch (Exception e) {
                try {
                    // 重试，等待 10 秒
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        // 关闭启动执行器
        bootstrapExecutor.shutdown();
    }

    /**
     * HTTP 服务器线程
     */
    private static class ServerThread implements Runnable {
        /**
         * 保存传递给构造函数的 ServerSocket 实例
         */
        private ServerSocket serverSocket;

        /**
         * 构造函数
         * @param s ServerSocket 实例
         */
        public ServerThread(ServerSocket s) {
            this.serverSocket = s;
        }

        /**
         * 线程执行的任务
         */
        @Override
        public void run() {
            while (true) {
                try {
                    // 等待客户端连接
                    System.out.println("等待客户端连接。。。");
                    Socket socket = this.serverSocket.accept();
                    System.out.println("收到一个客户端请求，ip:" + socket.getInetAddress());
                    // 创建一个 HttpTask 实例，将 Socket 实例作为参数传递
                    HttpTask eventTask = new HttpTask(socket);
                    // 将 HttpTask 提交给 taskExecutor 执行
                    taskExecutor.submit(eventTask);
                } catch (Exception e) {
                    log.error("服务端出现异常");
                    try {
                        // 如果发生异常，等待 1 秒后继续尝试
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}

