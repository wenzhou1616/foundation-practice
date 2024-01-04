package com.herry.code.practice.week08.rpc.client;

import com.herry.code.practice.week08.rpc.common.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Rpc调用者
 *
 * @author herry
 * @date 2024/1/3
 */
@Slf4j
public class RpcClient<T> {
    public static <T> T getRemoteProxyObj(final Class<?> target, final InetSocketAddress address) {
        // 1.将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
        return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class<?>[]{target},
                (proxy, method, args) -> {
                    // 将要执行的类名，方放名，参数类，参数发给服务端，阻塞等待服务端的结果回复，然后回复服务端的结果
                    Socket socket = null;
                    try {
                        // 2.创建Socket客户端，根据指定地址连接远程服务提供者
                        socket = new Socket();
                        socket.connect(address);

                        // 发送请求
                        // 3.将远程服务调用所需的接口类、方法名、参数列表等编码后发送给服务提供者
                        // 第6版，测试
//                        OutputStream os = socket.getOutputStream();
//                        os.write("hehehehe\n".getBytes(StandardCharsets.UTF_8));

//                        socket.shutdownOutput();

                        // 第5版，去掉缓冲流
//                        OutputStream os = socket.getOutputStream();
//                        Message message = new Message();
//                        message.setClassName(target.getName());
//                        message.setMethodName(method.getName());
//                        message.setParameterTypes(method.getParameterTypes());
//                        message.setArgs(args);
//                        String json = JSON.toJSONString(message);
//                        log.info("客户端发送请求："+json);
//                        os.write(json.getBytes(StandardCharsets.UTF_8));

                        // 第4版，去掉字符流
//                        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
//                        Message message = new Message();
//                        message.setClassName(target.getName());
//                        message.setMethodName(method.getName());
//                        message.setParameterTypes(method.getParameterTypes());
//                        message.setArgs(args);
//                        String json = JSON.toJSONString(message);
//                        log.info("客户端发送请求："+json);
//                        bos.write(json.getBytes(StandardCharsets.UTF_8));


                        // 第3版，将消息序列化为json，然后传输 不抱错但是不输出
//                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                        Message message = new Message();
//                        message.setClassName(target.getName());
//                        message.setMethodName(method.getName());
//                        message.setParameterTypes(method.getParameterTypes());
//                        message.setArgs(args);
//                        String json = JSON.toJSONString(message);
//                        log.info("客户端发送请求："+json);
//                        bw.write(json);

                        // 第2版，将消息封装成类
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        Message message = new Message();
                        message.setClassName(target.getName());
                        message.setMethodName(method.getName());
                        message.setParameterTypes(method.getParameterTypes());
                        message.setArgs(args);
                        output.writeObject(message);

                        // 第一版，不用对象封装
//                        output.writeUTF(target.getName());
//                        output.writeUTF(method.getName());
//                        output.writeObject(method.getParameterTypes());
//                        output.writeObject(args);

                        // 4.同步阻塞等待服务器返回应答，获取应答后返回
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

//                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        // 3.1 read()
//                        char[] data = new char[1024];
//
//                        int len;
//                        StringBuilder s = new StringBuilder();
//                        while ((len = br.read(data) )!= -1) {
//                            s.append(new String(data, 0, len));
//                            if (len < data.length) {
//                                break;
//                            }
//                            break;
//                        }
//                        String s1 = JSON.parseObject(s.toString(), String.class);

                        // 第4版
//                        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
//                        byte[] data = new byte[1024];
//                        int len;
//                        StringBuilder s = new StringBuilder();
//                        while ((len = bis.read(data)) != -1) {
//                            s.append(new String(data, 0, len));
//                            break;
//                        }
//                        String s1 = JSON.parseObject(s.toString(), String.class);

                        // 第5版
//                        InputStream is = socket.getInputStream();
//                        byte[] data = new byte[1024];
//                        int len;
//                        StringBuilder s = new StringBuilder();
//                        while ((len = is.read(data)) != -1) {
//                            s.append(new String(data, 0, len));
//                            break;
//                        }
//                        String s1 = JSON.parseObject(s.toString(), String.class);


//
//                        log.info("客户端接收到响应：" + s1);

                        return input.readObject();

                    } finally {
                        // 关闭 Socket 连接
                        try {
                            socket.close();
                        } catch (IOException e) {
                            log.error("关闭 Socket 连接失败", e);
                        }
                    }
                });
    }
}