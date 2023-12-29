package com.herry.code.practice.week07.chatSystem.server.service;

import com.herry.code.practice.week07.chatSystem.common.Message;
import com.herry.code.practice.week07.chatSystem.common.MessageType;
import com.herry.code.practice.week07.chatSystem.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 服务端
 *
 * @author herry
 * @date 2023/12/26
 */
public class ChatServer {
    /**
     * 用于保存用户信息
     */
    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();

    static {
        validUsers.put("tom", new User("tom", "123456"));
        validUsers.put("jerry", new User("jerry", "123456"));
        validUsers.put("Ice", new User("Ice", "123456"));
        validUsers.put("Five", new User("Five", "123456"));
        validUsers.put("Kie", new User("Kie", "123456"));
    }

    /**
     * 校验用户
     */
    public boolean checkUser(String userId, String password) {
        User user = validUsers.get(userId);
        if (user == null) {
            return false;
        }
        return user.getPwd().equals(password);
    }
    public ChatServer() {
        ServerSocket serverSocket = null;
        try {
            System.out.println("服务端正在8888端口监听...");
            serverSocket = new ServerSocket(8888);
 
            while (true) {
                // 获取Socket类对象
                Socket socket = serverSocket.accept();

                // 获取Socket对象关联的输入流与输出流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
 
                // 客户端第一次传过来的是User对象
                User user = (User) ois.readObject();
                
                // 创建一个Message对象，用于回复客户端是否连接成功
                Message message = new Message();
 
                if (checkUser(user.getId(), user.getPwd())) {
                    // 登录成功
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCESS);
                    // 将包含“登录成功与否”信息的Message对象写入数据通道
                    oos.writeObject(message);
                    ServerConnectClientThread scct = new ServerConnectClientThread(socket, user.getId());
                    scct.start();
                    ControlServerConnectClientThread.addServerConnectClientThread(user.getId(), scct);
                } else {
                    // 登录失败
                    System.out.println("id = " + user.getId() + ",pwd = " + user.getPwd() + " 验证失败！");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }
 
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}