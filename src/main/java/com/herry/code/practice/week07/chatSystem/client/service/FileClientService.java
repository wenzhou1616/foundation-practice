package com.herry.code.practice.week07.chatSystem.client.service;


import com.herry.code.practice.week07.chatSystem.common.Message;
import com.herry.code.practice.week07.chatSystem.common.MessageType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
 
/**
 * 发送文件
 *
 * @author herry
 * @date 2023/12/26
 */
public class FileClientService {
 
    /**
     * 发送文件
     *
     * @param souPath : 数据源文件路径
     * @param desPath : 目的地文件路径
     * @param sender : 发送者(ID)
     * @param receiver : 接收者(ID)
     */
    public void setFileToOne(String souPath, String desPath, String sender, String receiver) {
        Message message = new Message();
 
        message.setMesType(MessageType.MESSAGE_FILE_TRANSMISSION);
        message.setSouPath(souPath);
        message.setDesPath(desPath);
        message.setSender(sender);
        message.setReceiver(receiver);

        byte[] file = new byte[(int)new File(souPath).length()];

        try (FileInputStream fileInputStream = new FileInputStream(souPath)){
            fileInputStream.read(file);
            // 将文件包装到Message对象
            message.setFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
 
        // 提示信息
        System.out.println("\n" + sender + " 给 " + receiver + " 发送 " + souPath +
                " 到对方电脑的目录 " + desPath + " 下...");
 
        // 发送文件
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ControlClientConnectServiceThread
                    .getClientConnectServiceThread(sender).getSocket().getOutputStream());
 
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}