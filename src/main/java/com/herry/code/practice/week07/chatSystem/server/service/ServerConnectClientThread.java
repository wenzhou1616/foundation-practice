package com.herry.code.practice.week07.chatSystem.server.service;

import com.herry.code.practice.week07.chatSystem.common.Message;
import com.herry.code.practice.week07.chatSystem.common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 接受客户端消息，并回复
 *
 * @author herry
 * @date 2023/12/26
 */
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userId;
 
    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }
 
    public Socket getSocket() {
        return socket;
    }
 
    @Override
    public void run() {
        label:
        while (true) {
            try {
                System.out.println("服务端与客户端" + userId + "保持通讯，读取数据中...");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
 
                Message message = (Message) ois.readObject();

                switch (message.getMesType()) {
                    case MessageType.MESSAGE_GET_ONLINE_FRIENDS: {
                        System.out.println(message.getSender() + " 请求拉取在线用户列表。");
                        String onlineUsers = ControlServerConnectClientThread.getOnlineFriends();

                        //构建Message对象，将获取到的在线用户列表的信息发送给客户端
                        Message message2 = new Message();
                        message2.setMesType(MessageType.MESSAGE_RETURN_ONLINE_FRIENDS);
                        message2.setContent(onlineUsers);
                        message2.setReceiver(message.getSender());

                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(message2);
                        break;
                    }
                    case MessageType.MESSAGE_CLIENT_EXIT:
                        // 退出系统
                        System.out.println(message.getSender() + " 退出...");
                        // 删除线程前让当前线程休眠0.5秒，避免EOF异常
                        Thread.sleep(500);

                        ControlServerConnectClientThread.removeServerConnectClientThread(userId);
                        // 关闭Socket
                        socket.close();
                        // 退出while循环
                        break label;
                    case MessageType.MESSAGE_COMMON_MES_ALL:
                        // 遍历管理线程的集合
                        HashMap<String, ServerConnectClientThread> hashMap = ControlServerConnectClientThread.getHashMap();

                        for (String onlUser : hashMap.keySet()) {
                            // 排除自己
                            if (!onlUser.equals(message.getSender())) {
                                ObjectOutputStream oos =
                                        new ObjectOutputStream(hashMap.get(onlUser).getSocket().getOutputStream());
                                oos.writeObject(message);
                            }
                        }
                        break;
                    case MessageType.MESSAGE_COMMON_MES:
                    case MessageType.MESSAGE_FILE_TRANSMISSION: {
                        // 私发、发送文件
                        ObjectOutputStream oos = new ObjectOutputStream(ControlServerConnectClientThread
                                .getServerConnectClientThread(message.getReceiver()).getSocket().getOutputStream());
                        oos.writeObject(message);
                        break;
                    }
                    default:
                        System.out.println("...other content");
                        break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}