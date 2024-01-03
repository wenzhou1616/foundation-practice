package com.herry.code.demo.week08;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author herry
 * @date 2024/1/3
 */
public class ServerDome {
    public static void main(String[] args) {
        try {
            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            // 监听port端口，接受客户端的连接，获取客户端套接字
            Socket socket = serverSocket.accept();
            // 获取套接字的输入流，用于接收客户端的请求
            InputStream inputStream = socket.getInputStream();
            // 获取套接字的输出流，用于向客户端发送请求
            OutputStream outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
