package com.herry.code.practice.week07.chatsystem.server;

import com.herry.code.practice.week07.chatsystem.common.Message;
import com.herry.code.practice.week07.chatsystem.common.MessageType;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接收客户端消息，做出响应
 *
 * @author herry
 * @date 2023/12/26
 */
@Slf4j
public class ChatServerTask implements Runnable {
    private Socket socket;
    private String userId;

    public ChatServerTask(Socket socket, String userId) {
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
                        // 获取在线用户列表
                        System.out.println(message.getSender() + " 请求拉取在线用户列表。");
                        String onlineUsers = ServerThreadManager.getOnlineFriends();

                        // 构建Message对象，将获取到的在线用户列表的信息发送给客户端
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
                        ServerThreadManager.removeServerConnectClientThread(userId);
                        // 关闭Socket
                        socket.close();
                        // 退出while循环
                        break label;
                    case MessageType.MESSAGE_COMMON_MES_ALL:
                        // 发消息给所有人
                        ConcurrentHashMap<String, ChatServerTask> concurrentHashMap = ServerThreadManager
                                .getConcurrentHashMap();
                        for (String onlUser : concurrentHashMap.keySet()) {
                            // 排除自己
                            if (!onlUser.equals(message.getSender())) {
                                ObjectOutputStream oos = new ObjectOutputStream(concurrentHashMap.get(onlUser)
                                        .getSocket().getOutputStream());
                                oos.writeObject(message);
                            }
                        }
                        break;
                    case MessageType.MESSAGE_COMMON_MES:
                    case MessageType.MESSAGE_FILE_TRANSMISSION: {
                        // 私发、发送文件
                        ObjectOutputStream oos = new ObjectOutputStream(ServerThreadManager
                                .getServerConnectClientThread(message.getReceiver()).getSocket().getOutputStream());
                        oos.writeObject(message);
                        break;
                    }
                    default:
                        System.out.println("...other content");
                        break;
                }
            } catch (Exception e) {
                log.error("处理请求出错");
            }
        }
    }
}