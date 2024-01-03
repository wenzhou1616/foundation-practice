package com.herry.code.practice.week08.rpc.server;

/**
 * 服务接口
 *
 * @author herry
 * @date 2024/1/2
 */
public interface HelloService {
    /**
     * 远程方法，打印hi
     * @param name
     * @return
     */
    String sayHi(String name);
}