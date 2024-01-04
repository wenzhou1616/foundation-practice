package com.herry.code.practice.week08.rpc.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * RPC服务器
 *
 * @author herry
 * @date 2024/1/3
 */
@Slf4j
public class RpcServer {

    /**
     * 线程池
     */
    private static ExecutorService taskExecutor;

    /**
     * 服务端绑定的端口号
     */
    private static final int PORT = 9000;

    /**
     * 启动服务器
     */
    public static void staterRpcServer() {
        int threads = Runtime.getRuntime().availableProcessors();
        taskExecutor = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<>(100), new ThreadPoolExecutor.DiscardPolicy());

        // 为了确保服务器启动，用循环多次尝试启动
        while (true) {
            try {
                // 绑定端口
                ServerSocket serverSocket = new ServerSocket(PORT);
                // 启动服务器线程
                taskExecutor.submit(new ServerThread(serverSocket));
                // 成功了一次就退出循环
                break;
            } catch (IOException e) {
                try {
                    // 如果发生异常，等待10秒后重试
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    /**
     * 服务器线程
     */
    private static class ServerThread implements Runnable{

        /**
         * 服务端套接字
         */
        private ServerSocket serverSocket;

        /**
         * 构造函数
         * @param serverSocket
         */
        public ServerThread(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        /**
         * 线程的任务：不断接受客户端请求，对每个连接都开启线程去执行请求
         */
        @Override
        public void run() {
            // 注册服务
            ServerTask.register(HelloService.class, HelloServiceImpl.class);

            // 用循环不断接受客户端的连接
            while (true) {
                try {
                    System.out.println("等待客户端连接。。。");
                    // 接受客户端的连接，获取客户端的套接字
                    Socket socket = serverSocket.accept();
                    System.out.println("接收到一个客户端连接，ip: " + socket.getInetAddress());
                    // 将客户端套接字交给服务器任务
                    ServerTask serverTask = new ServerTask(socket);
                    // 执行服务器任务
                    taskExecutor.submit(serverTask);
                } catch (IOException e) {
                    log.error("服务器出现异常", e);
                    try {
                        // 如果出现了异常，等待1秒重试
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

        }
    }


}
