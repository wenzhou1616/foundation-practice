package com.herry.code.practice.week08.rpc;

import com.herry.code.practice.week08.rpc.client.RpcClient;
import com.herry.code.practice.week08.rpc.server.HelloService;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 测试 RPC
 *
 * @author herry
 * @date 2024/1/3
 */
public class RpcTest {
    public static void main(String[] args) throws IOException {
        HelloService service = RpcClient.getRemoteProxyObj(HelloService.class,
                new InetSocketAddress("localhost", 9000));
        System.out.println(service.sayHi("test"));
    }
}