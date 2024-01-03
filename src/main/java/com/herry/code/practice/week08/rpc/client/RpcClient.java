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
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        Message message = new Message();
                        message.setClassName(target.getName());
                        message.setMethodName(method.getName());
                        message.setParameterTypes(method.getParameterTypes());
                        message.setArgs(args);
                        output.writeObject(message);

//                        output.writeUTF(target.getName());
//                        output.writeUTF(method.getName());
//                        output.writeObject(method.getParameterTypes());
//                        output.writeObject(args);

                        // 4.同步阻塞等待服务器返回应答，获取应答后返回
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        return input.readObject();
                    } finally {
                        // 关闭 Socket 连接
                        try {
                            socket.close();
                        } catch (IOException e) {
                            log.error("关闭 Socket 连接失败");
                        }
                    }
                });
    }
}