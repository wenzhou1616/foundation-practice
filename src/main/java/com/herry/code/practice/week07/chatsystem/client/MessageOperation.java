package com.herry.code.practice.week07.chatsystem.client;

import com.herry.code.practice.week07.chatsystem.common.Message;
import com.herry.code.practice.week07.chatsystem.common.MessageType;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectOutputStream;
 
/**
 * 消息操作
 *
 * @author herry
 * @date 2023/12/26
 */
@Slf4j
public class MessageOperation {
    /**
     * 私发消息
     *
     * @param receiver : 消息的接收者
     * @param content : 消息内容
     * @param sender : 消息的发送者
     */
    public static void sendMessageToOne(String receiver, String content, String sender) {
        Message message = new Message();
 
        message.setMesType(MessageType.MESSAGE_COMMON_MES);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setSender(sender);
        message.setSendTime(new java.util.Date().toString());

        try {
            // 获取发送消息的用户的输出流对象，并将上面的消息发给服务端
            ChatClientTask chatClientTask = ClientThreadManager.getClientConnectServiceThread(sender);
            ObjectOutputStream oos = new ObjectOutputStream(chatClientTask.getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(sender + " 对 " + receiver + " 说 \"" + content + "\"");
        } catch (Exception e) {
            log.error("发送失败", e);
        }
    }
 
    /**
     * 群发消息
     *
     * @param content : 群发消息的内容
     * @param sender : 群发消息的发送者
     */
    public static void sendMessageToAll(String content, String sender) {
        Message message = new Message();
 
        message.setMesType(MessageType.MESSAGE_COMMON_MES_ALL);
        message.setContent(content);
        message.setSender(sender);
        message.setSendTime(new java.util.Date().toString());
        try {
            // 获取发送消息的用户的输出流对象，并将上面的消息发给服务端
            ChatClientTask chatClientTask = ClientThreadManager.getClientConnectServiceThread(sender);
            ObjectOutputStream oos = new ObjectOutputStream(chatClientTask.getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(sender + " 对所有在线的用户说 \"" + content + "\"");
        } catch (Exception e) {
            log.error("发送失败", e);
        }
    }
}
