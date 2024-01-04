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
 * Rpc 客户端
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
                    // 将要执行的类名，方放名，参数类型，参数发给服务端
                    Socket socket = null;
                    try {
                        // 创建Socket客户端，根据指定地址连接远程服务提供者
                        socket = new Socket();
                        socket.connect(address);

                        // 将远程服务调用所需的类名、方法名、参数列表、参数封装成消息发送给服务端
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        Message message = new Message();
                        message.setClassName(target.getName());
                        message.setMethodName(method.getName());
                        message.setParameterTypes(method.getParameterTypes());
                        message.setArgs(args);
                        output.writeObject(message);

                        // 等待服务器回复，将回复返回
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
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