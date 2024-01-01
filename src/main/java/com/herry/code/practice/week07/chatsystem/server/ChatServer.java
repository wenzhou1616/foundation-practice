package com.herry.code.practice.week07.chatsystem.server;

import com.herry.code.practice.week07.chatsystem.common.Message;
import com.herry.code.practice.week07.chatsystem.common.MessageType;
import com.herry.code.practice.week07.chatsystem.common.User;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


/**
 * 服务端
 *
 * @author herry
 * @date 2023/12/26
 */
@Slf4j
public class ChatServer {
    /**
     * 用于保存用户信息
     */
    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();

    static {
        validUsers.put("user01", new User("user01", "123456"));
        validUsers.put("user02", new User("user02", "123456"));
        validUsers.put("user03", new User("user03", "123456"));
        validUsers.put("user04", new User("user04", "123456"));
        validUsers.put("user05", new User("user05", "123456"));
    }

    /**
     * 初始化线程池，设置线程池大小，队列大小和丢弃策略
     */
    private  static ExecutorService serverTaskExecutor;

    /**
     * 用于启动聊天服务器的线程池
     */
    private static ExecutorService bootstrapExecutor = Executors.newSingleThreadExecutor();

    /**
     * 服务器监听端口号
     */
    private static final int PORT = 8888;

    /**
     * 启动聊天服务器
     */
    public static void startChatServer() {
        // 获取处理器可用核心数，用于设置线程池大小
        int nThreads = Runtime.getRuntime().availableProcessors();
        // 初始化线程池，设置线程池大小，队列大小和丢弃策略
        serverTaskExecutor = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100), new ThreadPoolExecutor.DiscardPolicy());

        // 循环尝试启动服务器，如果启动失败，则等待10秒后重试
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                bootstrapExecutor.submit(new ChatServer.ServerThread(serverSocket));
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
     * 聊天服务器线程
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

                    // 获取Socket对象关联的输入流与输出流
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                    // 客户端第一次传过来的是User对象
                    User user = (User) ois.readObject();

                    // 创建一个Message对象，用于回复客户端
                    Message message = new Message();

                    if (checkUser(user.getId(), user.getPwd())) {
                        // 登录成功
                        message.setMesType(MessageType.MESSAGE_LOGIN_SUCCESS);
                        // 将包含“登录成功”信息的Message对象写入数据通道
                        oos.writeObject(message);
                        ChatServerTask chatServerTask = new ChatServerTask(socket, user.getId());
                        serverTaskExecutor.submit(chatServerTask);
                        ServerThreadManager.addServerConnectClientThread(user.getId(), chatServerTask);
                    } else {
                        // 登录失败
                        System.out.println("id = " + user.getId() + ",pwd = " + user.getPwd() + " 验证失败！");
                        message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                        oos.writeObject(message);
                        socket.close();
                    }
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

    /**
     * 校验用户
     */
    public static boolean checkUser(String userId, String password) {
        User user = validUsers.get(userId);
        if (user == null) {
            return false;
        }
        return user.getPwd().equals(password);
    }
}