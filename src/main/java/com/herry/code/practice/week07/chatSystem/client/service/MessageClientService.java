package com.herry.code.practice.week07.chatSystem.client.service;

import com.herry.code.practice.week07.chatSystem.common.Message;
import com.herry.code.practice.week07.chatSystem.common.MessageType;
import java.io.ObjectOutputStream;
 
/**
 * 发送消息
 *
 * @author herry
 * @date 2023/12/26
 */
public class MessageClientService {
    /**
     * 私发消息
     *
     * @param receiver : 消息的接收者
     * @param content : 消息内容
     * @param sender : 消息的发送者
     */
    public void sendMessageToOne(String receiver, String content, String sender) {
        Message message = new Message();
 
        message.setMesType(MessageType.MESSAGE_COMMON_MES);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setSender(sender);
        message.setSendTime(new java.util.Date().toString());
        System.out.println(sender + " 对 " + receiver + " 说 \"" + content + "\"");
 
        try {
            // 获取发送消息的用户的输出流对象，并将上面的消息发给服务端
            ClientConnectServiceThread clientConnectServiceThread = ControlClientConnectServiceThread.getClientConnectServiceThread(sender);
            ObjectOutputStream oos = new ObjectOutputStream(clientConnectServiceThread.getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    /**
     * 群发消息
     *
     * @param content : 群发消息的内容
     * @param sender : 群发消息的发送者
     */
    public void sendMessageToAll(String content, String sender) {
        Message message = new Message();
 
        message.setMesType(MessageType.MESSAGE_COMMON_MES_ALL);
        message.setContent(content);
        message.setSender(sender);
        message.setSendTime(new java.util.Date().toString());
        System.out.println(sender + " 对所有在线的用户说 \"" + content + "\"");
 
        try {
            // 获取发送消息的用户的输出流对象，并将上面的消息发给服务端
            ClientConnectServiceThread clientConnectServiceThread = ControlClientConnectServiceThread.getClientConnectServiceThread(sender);
            ObjectOutputStream oos = new ObjectOutputStream(clientConnectServiceThread.getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}