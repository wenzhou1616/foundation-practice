package com.herry.code.practice.week07.chatSystem.client.service;
 
import java.util.HashMap;
 
/**
 * 控制客户端线程
 *
 * @author herry
 * @date 2023/12/26
 */
public class ControlClientConnectServiceThread {
    /**
     * 存放线程
     */
    private static HashMap<String, ClientConnectServiceThread> hashMap = new HashMap<>();

    /**
     * 添加线程
     */
    public static void addClientConnectServiceThread(String userId, ClientConnectServiceThread ccst) {
        hashMap.put(userId, ccst);
    }

    /**
     * 取出线程
     */
    public static ClientConnectServiceThread getClientConnectServiceThread(String userId) {
        return hashMap.get(userId);
    }
}