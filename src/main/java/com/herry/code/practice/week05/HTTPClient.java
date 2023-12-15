package com.herry.code.practice.week05;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 测试模拟的 HTTP 服务器，客户端
 * @author herry
 */
public class HTTPClient {
    public static void main(String[] args) throws IOException {
        // 创建客服端，连接服务端
        Socket socket = new Socket("127.0.0.1", 9000);

        // 用输出流给服务端发请求
        OutputStream outputStream = socket.getOutputStream();
        String message = "POST /login HTTP/1.1\n" +
                "Host: Host: www.baidu.com\n" +
                "User-Agent: Mozilla/5.0\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 29\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                "\n" +
                "username=欧阳天一&password=123456\n";
        outputStream.write(message.getBytes());
        // 关闭输出流
        socket.shutdownOutput();

        // 用输入流接收服务端的响应
        InputStream inputStream = socket.getInputStream();
        byte[] data = new byte[1024];
        StringBuilder s = new StringBuilder();
        int len;
        while ((len = inputStream.read(data)) != -1) {
            s.append(new String(data, 0, len));
        }
        System.out.println("接收到了服务端的响应:\n" + s);

        // 关闭socket，输入输出流也会关
        socket.close();

    }
}
