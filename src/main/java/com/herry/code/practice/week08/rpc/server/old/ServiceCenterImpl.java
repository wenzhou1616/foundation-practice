package com.herry.code.practice.week08.rpc.server.old;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务中心，将服务发布成远程服务，供消费者使用
 *
 * @author herry
 * @date 2024/1/3
 */
public class ServiceCenterImpl implements ServerCenter {
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final HashMap<String, Class> serviceRegistry = new HashMap<>();
    private static boolean isRunning = false;
    private static int port;

    public ServiceCenterImpl(int port) {
        ServiceCenterImpl.port = port;
    }

    @Override
    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    /**
     * 开启服务端
     * @throws IOException
     */
    public void start() throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        System.out.println("start server");
        try {
            while (true) {
                // 1.监听客户端的TCP连接，接到TCP连接后将其封装成task，由线程池执行
                executor.execute(new ServiceTask(server.accept()));
            }
        } finally {
            server.close();
        }
    }

    public void register(Class serviceInterface, Class impl) {
        serviceRegistry.put(serviceInterface.getName(), impl);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getPort() {
        return port;
    }

    private static class ServiceTask implements Runnable {
        Socket clent = null;

        public ServiceTask(Socket client) {
            this.clent = client;
        }

        /**
         * 接收客户端的请求，用反射调用方法，将结果发送给客户端
         */
        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                // 2.将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
                input = new ObjectInputStream(clent.getInputStream());
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Class serviceClass = serviceRegistry.get(serviceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + " not found");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);
                // 3.将执行结果反序列化，通过socket发送给客户端
                output = new ObjectOutputStream(clent.getOutputStream());
                output.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (clent != null) {
                    try {
                        clent.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}