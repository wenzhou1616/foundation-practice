package com.herry.code.practice.week07.chatsystem.client;
 
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理客户端任务线程
 *
 * @author herry
 * @date 2023/12/26
 */
public class ClientThreadManager {
    /**
     * 保存线程
     */
    private static ConcurrentHashMap<String, ChatClientTask> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 添加线程
     */
    public static void addClientConnectServiceThread(String userId, ChatClientTask chatClientTask) {
        concurrentHashMap.put(userId, chatClientTask);
    }

    /**
     * 取出线程
     */
    public static ChatClientTask getClientConnectServiceThread(String userId) {
        return concurrentHashMap.get(userId);
    }
}
