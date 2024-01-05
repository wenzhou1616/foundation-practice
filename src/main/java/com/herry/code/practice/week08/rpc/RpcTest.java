package com.herry.code.practice.week08.rpc;

import com.herry.code.practice.week08.rpc.client.RpcClient;
import com.herry.code.practice.week08.rpc.server.HelloService;

/**
 * @author herry
 * @date 2024/1/5
 */
public class RpcTest {
    public static void main(String[] args) {
        HelloService helloService = RpcClient.getRemoteProxyObj(HelloService.class);
        helloService.sayHi("test");
    }
}
