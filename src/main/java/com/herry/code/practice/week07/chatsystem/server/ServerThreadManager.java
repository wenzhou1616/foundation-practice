package com.herry.code.practice.week07.chatsystem.server;
 
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理服务端任务线程
 *
 * @author herry
 * @date 2023/12/26
 */
public class ServerThreadManager {
    private static ConcurrentHashMap<String, ChatServerTask> concurrentHashMap = new ConcurrentHashMap<>();
 
    public static ConcurrentHashMap<String, ChatServerTask> getConcurrentHashMap() {
        return concurrentHashMap;
    }

    /**
     * 添加线程
     */
    public static void addServerConnectClientThread(String userID, ChatServerTask scct) {
        concurrentHashMap.put(userID, scct);
    }
 
    /**
     * 根据用户的id获取对应的线程
     */
    public static ChatServerTask getServerConnectClientThread(String userID) {
        return concurrentHashMap.get(userID);
    }

    /**
     * 获取在线用户列表
     */
    public static String getOnlineFriends() {
        Iterator<String> iterator = concurrentHashMap.keySet().iterator();
        StringBuilder onlineUsers = new StringBuilder();
        while (iterator.hasNext()) {
            onlineUsers.append(iterator.next()).append(" ");
        }
        return onlineUsers.toString();
    }

    /**
     * 删除线程
     */
    public static void removeServerConnectClientThread(String userID) {
        concurrentHashMap.remove(userID);
    }
}
