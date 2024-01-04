package com.herry.code.practice.week08.rpc;

import com.herry.code.practice.week08.rpc.client.RpcClient;
import com.herry.code.practice.week08.rpc.server.HelloService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {
    public static void main(String[] args) throws IOException {
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    ServerCenter serviceCenter = new ServiceCenterImpl(8088);
//                    serviceCenter.register(HelloService.class, HelloServiceImpl.class);
//                    serviceCenter.start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        RpcServer.staterRpcServer();
        HelloService service = RpcClient.getRemoteProxyObj(HelloService.class,
                new InetSocketAddress("localhost", 9000));
        System.out.println(service.sayHi("test"));

//        Attempted to serialize java.lang.Class: java.lang.String. Forgot to register a type adapter?
    }
}