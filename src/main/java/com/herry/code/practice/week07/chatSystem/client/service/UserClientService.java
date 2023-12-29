package com.herry.code.practice.week07.chatSystem.client.service;

import com.herry.code.practice.week07.chatSystem.common.Message;
import com.herry.code.practice.week07.chatSystem.common.MessageType;
import com.herry.code.practice.week07.chatSystem.common.User;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 向服务端发起校验登录、拉取在线用户列表、退出登录
 *
 * @author herry
 * @date 2023/12/26
 */
@Slf4j
public class UserClientService {
    private final User user = new User();

    /**
     * 向服务端发起用户登录的验证
     */
    public boolean check(String userId, String password) throws IOException {
        // 校验结果
        boolean result = false;
        // 初始化User对象
        user.setId(userId);
        user.setPwd(password);

        // 向服务端发送信息
        try {
            // 获取Socket对象
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8888);
            // 获取与Socket对象相关联的对象处理流(输出流)
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            // 序列化User对象，写入数据通道（向服务端发送一个User对象，服务端会对这个User对象进行验证）
            oos.writeObject(user);
            // 获取与Socket对象相关联的对象处理流(输入流)
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // 读取服务端传输过来的Message对象
            Message message = (Message) ois.readObject();

            if (message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)) {
                // 校验成功
                ClientConnectServiceThread ccst = new ClientConnectServiceThread(socket);
                ccst.start();
                ControlClientConnectServiceThread.addClientConnectServiceThread(userId, ccst);
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
    public void onlineList() {
        // 向服务端发送一个Message对象，类型是MESSAGE_GET_ONLINE_FRIENDS
        Message message = new Message();
        // 用户登录时已在check方法中设置了id的值
        message.setSender(user.getId());
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIENDS);

        try {
            // 得到当前线程持有的Socket对象对应的对象处理流（输出流）
            ObjectOutputStream oos = new ObjectOutputStream(ControlClientConnectServiceThread.
                    getClientConnectServiceThread(user.getId()).getSocket().getOutputStream());
            // 向服务端发送“拉取在线用户列表”的请求
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 退出当前用户
     */
    public void logout() {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(user.getId());

        // 发送Message对象
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ControlClientConnectServiceThread
                    .getClientConnectServiceThread(user.getId()).getSocket().getOutputStream());
            oos.writeObject(message);

            ControlClientConnectServiceThread.getClientConnectServiceThread(user.getId()).setLoop(false);
            System.out.println(user.getId() + " 退出系统...");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}