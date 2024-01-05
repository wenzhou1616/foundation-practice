package com.herry.code.practice.week08.rpc.client;

import com.herry.code.practice.week08.rpc.common.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Rpc 客户端
 *
 * @author herry
 * @date 2024/1/3
 */
@Slf4j
public class RpcClient<T> implements InvocationHandler {

    private final String IP = "127.0.0.1";

    private final int PORT = 9000;

    private Class target;

    public RpcClient(Class target) {
        this.target = target;
    }

    /**
     * 得到被代理对象
     */
    public static <T> T getRemoteProxyObj(Class target) {
        return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, new RpcClient(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 将要执行的类名，方放名，参数类型，参数发给服务端
        Socket socket = null;
        try {
            // 创建Socket客户端，根据指定地址连接远程服务提供者
            socket = new Socket(IP, PORT);

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
    }
}