package com.herry.code.practice.week05;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 处理 HTTP 请求
 *
 * @author herry
 */
@Slf4j
public class HttpTask implements Runnable {
    /**
     * 用于处理 HTTP 请求的 Socket
     */
    private Socket socket;

    /**
     * 记录访问次数
     */
    private static AtomicInteger counter = new AtomicInteger();


    /**
     * 构造一个新的 HttpTask，用于处理指定的 Socket 连接
     *
     * @param socket 用于处理 HTTP 请求的 Socket
     */
    public HttpTask(Socket socket) {
        this.socket = socket;
    }

    /**
     * 实现 Runnable 接口的 run 方法，用于处理 HTTP 请求并发送响应消息
     */
    @Override
    public void run() {
        // 检查 socket 是否为 null，如果为 null 则抛出异常
        if (socket == null) {
            throw new IllegalArgumentException("socket 不能为 null");
        }

        try {
            // 获取 Socket 的输出流，并创建一个 PrintWriter 对象
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream);

            // 获取IP
            InetAddress inetAddress = socket.getInetAddress();

            // 从 Socket 的输入流中解析 HTTP 请求
            HttpMessageParser.Request httpRequest = HttpMessageParser.parse2request(socket.getInputStream());

            try {
                // 响应正文
                // 获取当前日期
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                // 访问次数加一
                int count = counter.incrementAndGet();
                String message = formattedDateTime + " [HTTP] " + "[第" + count + "次访问] 服务端已收到请求";

                // 根据请求和结果构建 HTTP 响应
                String httpRes = HttpMessageParser.buildResponse(httpRequest, message, inetAddress);

                // 将 HTTP 响应发送到客户端
                out.print(httpRes);
            } catch (Exception e) {
                // 如果发生异常，构建一个包含异常信息的 HTTP 响应
                String httpRes = HttpMessageParser.buildResponse(httpRequest, e.toString(),inetAddress);
                out.print(httpRes);
            }

            // 刷新输出流，确保响应消息被发送
            out.flush();
        } catch (IOException e) {
            log.error("处理HTTP请求出现异常");
        } finally {
            // 关闭 Socket 连接
            try {
                socket.close();
            } catch (IOException e) {
                log.error("关闭 Socket 连接失败");
            }
        }
    }
}
