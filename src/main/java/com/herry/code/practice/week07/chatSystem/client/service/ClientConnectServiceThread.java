package com.herry.code.practice.week07.chatSystem.client.service;


import com.herry.code.practice.week07.chatSystem.common.Message;
import com.herry.code.practice.week07.chatSystem.common.MessageType;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
 
/**
 * 接受服务端消息，进行相应操作
 *
 * @author herry
 * @date 2023/12/26
 */
@Slf4j
public class ClientConnectServiceThread extends Thread {
    private Socket socket;
    private boolean loop = true;
 
    public ClientConnectServiceThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
 
    @Override
    public void run() {
        while (loop) {
            try {
                System.out.println("客户端线程，等待读取来自服务器端的消息...");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                // 如果服务端没有向客户端发消息，线程就会阻塞在这里
                Message message = (Message) ois.readObject();

                // 判断客户端读取到的Message的类型，并做出相应的业务处理
                switch (message.getMesType()) {
                    case MessageType.MESSAGE_RETURN_ONLINE_FRIENDS:
                        // 返回在线用户列表
                        String[] onlineUsers = message.getContent().split(" ");
                        System.out.println("\n===========在线用户列表如下：===========");
                        for (String onlineUser : onlineUsers) {
                            System.out.println("用户: " + onlineUser);
                        }
                        break;
                    case MessageType.MESSAGE_COMMON_MES_ALL:
                        // 对所有人发消息
                        System.out.println("\n" + message.getSender() + " 对所有在线的用户说 \"" +
                                message.getContent() + "\"");
                        break;
                    case MessageType.MESSAGE_COMMON_MES:
                        // 对某个人发消息
                        System.out.println("\n" + message.getSender() + " 对 " +
                                message.getReceiver() + " 说 \"" + message.getContent() + "\"");
                        break;
                    case MessageType.MESSAGE_FILE_TRANSMISSION:
                        // 接收文件
                        System.out.println("\n" + message.getSender() + " 给 " + message.getReceiver() + " 发送 " +
                                message.getSouPath() + " 到对方电脑的目录 " + message.getDesPath() + "下...");
                        FileOutputStream fileOutputStream = new FileOutputStream(message.getDesPath());
                        fileOutputStream.write(message.getFile());
                        fileOutputStream.close();
                        System.out.println("\n保存文件成功！");
                        break;
                    default:
                        System.out.println("...other content");
                        break;
                }
            } catch (Exception e) {
                log.error("接收消息出错");
            }
        }
    }
}