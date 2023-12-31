package com.herry.code.practice.week08.rpc.server;

import com.herry.code.practice.week08.rpc.common.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器的任务，处理客户端的请求
 *
 * @author herry
 * @date 2024/1/3
 */
@Slf4j
public class ServerTask implements Runnable{
    /**
     * 客户端套接字
     */
    private Socket socket;

    /**
     * 保存登记的服务
     */
    private static ConcurrentHashMap<String, Class<?>> serviceRegistry = new ConcurrentHashMap<>();

    /**
     * 构造函数
     * @param socket
     */
    public ServerTask(Socket socket) {
        this.socket = socket;
    }

    public static void register(Class serviceInterface, Class impl) {
        serviceRegistry.put(serviceInterface.getName(), impl);
    }

    /**
     * 接受客户端的请求，处理，返回结果
     */
    @Override
    public void run() {
        if (socket == null) {
            throw new IllegalArgumentException("socket不能为null");
        }

        try {
            // 解析客户端发的请求
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Message message = (Message) input.readObject();
            String serviceName = message.getClassName();
            String methodName = message.getMethodName();
            Class<?>[] parameterTypes = message.getParameterTypes();
            Object[] arguments = message.getArgs();

            // 根据方法名获取登记的类
            Class<?> serviceClass = serviceRegistry.get(serviceName);
            if (serviceClass == null) {
                throw new ClassNotFoundException(serviceName + " not found");
            }
            // 用反射创建对应类名的对象
            Method method = serviceClass.getMethod(methodName, parameterTypes);
            Constructor<?> constructor = serviceClass.getConstructor();
            Object result = method.invoke(constructor.newInstance(), arguments);

            // 将结果返回给客户端
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(result);
        } catch (Exception e) {
            log.error("处理客户端请求出现异常", e);
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
