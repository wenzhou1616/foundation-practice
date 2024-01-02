package com.herry.code.practice.week07.chatsystem.client;

import com.herry.code.practice.week07.chatsystem.common.Message;
import com.herry.code.practice.week07.chatsystem.common.MessageType;
import com.herry.code.practice.week07.chatsystem.common.User;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 登录相关操作
 *
 * @author herry
 * @date 2023/12/26
 */
@Slf4j
public class LoginOperation {
    /**
     * 获取处理器可用核心数，用于设置线程池大小
     */
    private static final int THREADS = Runtime.getRuntime().availableProcessors();

    /**
     * 初始化线程池，设置线程池大小，队列大小和丢弃策略
     */
    private  static ExecutorService clientTaskExecutor = new ThreadPoolExecutor(THREADS, THREADS, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100), new ThreadPoolExecutor.DiscardPolicy());

    /**
     * 向服务端发起用户登录的验证
     */
    public static boolean check(String userId, String password) {
        // 校验结果
        boolean result = false;
        // 初始化User对象
        User user = new User();
        user.setId(userId);
        user.setPwd(password);

        // 向服务端发送信息
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8888);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();

            if (message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)) {
                // 校验成功
                ChatClientTask chatClientTask = new ChatClientTask(socket);
                clientTaskExecutor.submit(new ChatClientTask(socket));
                ClientThreadManager.addClientConnectServiceThread(userId, chatClientTask);
                result = true;
            } else {
                // 校验失败
                socket.close();
            }
        } catch (Exception e) {
            log.error("登录校验失败");
        }
        return result;
    }

    /**
     * 向服务端请求获取在线用户列表
     */
    public static void onlineList(String userId) {
        // 向服务端发送一个Message对象，类型是MESSAGE_GET_ONLINE_FRIENDS
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIENDS);
        message.setSender(userId);

        try {
            // 向服务端发送“拉取在线用户列表”的请求
            ObjectOutputStream oos = new ObjectOutputStream(ClientThreadManager.
                    getClientConnectServiceThread(userId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            log.error("获取在线用户列表失败");
        }
    }

    /**
     * 退出当前用户
     */
    public static void logout(String userId) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(userId);

        try {
            // 向服务端发送“退出系统”的请求
            ObjectOutputStream oos = new ObjectOutputStream(ClientThreadManager
                    .getClientConnectServiceThread(userId).getSocket().getOutputStream());
            oos.writeObject(message);

            ClientThreadManager.getClientConnectServiceThread(userId).setLoop(false);
            System.out.println(userId + " 退出系统...");
            System.exit(0);
        } catch (IOException e) {
            log.error("退出失败");
        }
    }
}
