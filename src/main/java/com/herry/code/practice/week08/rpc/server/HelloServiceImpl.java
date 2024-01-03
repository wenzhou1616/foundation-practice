package com.herry.code.practice.week08.rpc.server;

/**
 * 服务接口实现类
 *
 * @author herry
 * @date 2024/1/2
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String name) {
        return "Hi, " + name;
    }
}