package com.herry.code.dome;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        // 创建 发送 接受 关闭
        // 创建客户端套接字，连接服务端，要指明服务端的ip和端口号
        // 要先启动服务端，再启动客户端，因为服务端不存在会报错
        Socket socket = new Socket("127.0.0.1", 12345);

        // 给服务端发数据，用输出流
        OutputStream outputStream = socket.getOutputStream();
        // TCP发字节
        outputStream.write("hello server".getBytes());
        // 关闭输出流
        socket.shutdownOutput();

        // 接收服务端发送的信息，用输入流
        InputStream inputStream = socket.getInputStream();
        byte[] data = new byte[1024];
        StringBuilder s = new StringBuilder();
        int len;
        while ((len = inputStream.read(data)) != -1) {
            s.append(new String(data, 0, len));
        }
        System.out.println("接受到服务端的信息了:" + s);

        // 关闭socket，输入输出流也会关
        socket.close();

    }
}