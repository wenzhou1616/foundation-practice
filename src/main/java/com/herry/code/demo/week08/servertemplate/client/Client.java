package com.herry.code.demo.week08.servertemplate.client;

import com.herry.code.demo.week08.servertemplate.common.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 *
 * @author herry
 * @date 2024/1/3
 */
@Slf4j
public class Client {
    /**
     * 端口号
     */
    private static final int PORT = 9000;

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", PORT)) {
            // 向服务端发请求
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.setAttribute01("i am client");
            output.writeObject(message);

            // 接收服务端的回复
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Message message1 = (Message) input.readObject();
            System.out.println(message1);

//            // 给服务端发数据，用输出流
//            OutputStream outputStream = socket.getOutputStream();
//            outputStream.write("hello server".getBytes());
//            // 关闭输出流,不然服务端的read会阻塞
//            socket.shutdownOutput();
//
//            // 接收服务端发送的信息，用输入流
//            InputStream inputStream = socket.getInputStream();
//            byte[] data = new byte[1024];
//            StringBuilder s = new StringBuilder();
//            int len;
//            while ((len = inputStream.read(data)) != -1) {
//                s.append(new String(data, 0, len));
//            }
//            System.out.println("接受到服务端的信息了:" + s);

        } catch (IOException | ClassNotFoundException e) {
            log.error("客户端出现异常", e);
        }

    }
}