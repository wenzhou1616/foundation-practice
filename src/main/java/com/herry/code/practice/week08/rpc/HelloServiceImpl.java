package com.herry.code.practice.week08.rpc;

/**
 * @author herry
 * @date 2024/1/2
 */
public class HelloServiceImpl implements HelloService {
    public String sayHi(String name) {
        return "Hi, " + name;
    }
}