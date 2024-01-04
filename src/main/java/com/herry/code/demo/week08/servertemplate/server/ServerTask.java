package com.herry.code.demo.week08.servertemplate.server;

import com.herry.code.demo.week08.servertemplate.common.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器的任务，处理客户端的请求
 *
 * @author herry
 * @date 2024/1/3
 */
@Slf4j
public class ServerTask implements Runnable{
    /**
     * 客户端套接字
     */
    private Socket socket;

    /**
     * 构造函数
     * @param socket
     */
    public ServerTask(Socket socket) {
        this.socket = socket;
    }

    /**
     * 接受客户端的请求，处理，返回结果
     */
    @Override
    public void run() {
        if (socket == null) {
            throw new IllegalArgumentException("socket不能为null");
        }

        try {
            // 解析客户端发的请求
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) input.readObject();

            // 服务端处理请求。。。

            // 假回复
            Message message1 = new Message();
            message1.setAttribute01("服务端回复了");
            // 将结果返回给客户端
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(message1);
        } catch (Exception e) {
            log.error("处理客户端请求出现异常", e);
        } finally {
            // 关闭 Socket 连接
            try {
                socket.close();
            } catch (IOException e) {
                log.error("关闭 Socket 连接失败", e);
            }
        }
    }
}
