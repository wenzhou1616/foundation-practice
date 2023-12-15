package com.herry.code.demo.week05;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        // 创建并绑定端口号，等待连接，打印主机
        // 创建服务端套接字
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("等待客户端连接。。。");
        // 接受客户端的连接，获取客户端套接字，这是一个阻塞方法，如果没有客户端连接，就会一直阻塞
        Socket socket = serverSocket.accept();
        InetAddress inetAddress = socket.getInetAddress();
        System.out.println("连接到的客户端的ip地址是：" + inetAddress);

        // 接受客户端发的数据，用输入流接收
        InputStream inputStream = socket.getInputStream();
        byte[] data = new byte[1024];
        StringBuilder s = new StringBuilder();
        int len;
        while ((len = inputStream.read(data)) != -1) {
            s.append(new String(data, 0, len));
        }
        System.out.println("接收到客户端的数据为：" + s);

        // 回复客户端，用输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("好的客户端，我收到了".getBytes());
        // 刷新缓冲区，将缓冲区中所有的数据输出到客户端
        outputStream.flush();

        // 关闭客户端socket，输入输出流也会关
        socket.close();

        // 关闭服务端socket
        serverSocket.close();

    }
}