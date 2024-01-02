package com.herry.code.practice.week07.chatsystem.client;


import com.herry.code.practice.week07.chatsystem.common.Message;
import com.herry.code.practice.week07.chatsystem.common.MessageType;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
 
/**
 * 文件操作
 *
 * @author herry
 * @date 2023/12/26
 */
@Slf4j
public class FileOperation {
 
    /**
     * 发送文件
     *
     * @param souPath : 数据源文件路径
     * @param desPath : 目的地文件路径
     * @param sender : 发送者(ID)
     * @param receiver : 接收者(ID)
     */
    public static void setFileToOne(String souPath, String desPath, String sender, String receiver) {
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
            // 发送文件
            ObjectOutputStream oos = new ObjectOutputStream(ClientThreadManager
                    .getClientConnectServiceThread(sender).getSocket().getOutputStream());
            oos.writeObject(message);
            // 提示信息
            System.out.println("\n" + sender + " 给 " + receiver + " 发送 " + souPath +
                    " 到对方电脑的目录 " + desPath + " 下...");
        }  catch (IOException e) {
            log.error("文件发送失败");
        }
    }
}
