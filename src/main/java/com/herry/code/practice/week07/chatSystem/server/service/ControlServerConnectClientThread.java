package com.herry.code.practice.week07.chatSystem.server.service;
 
import java.util.HashMap;
import java.util.Iterator;
 
/**
 * 控制服务端线程
 *
 * @author herry
 * @date 2023/12/26
 */
public class ControlServerConnectClientThread {
    private static HashMap<String, ServerConnectClientThread> hashMap = new HashMap<>();
 
    public static HashMap<String, ServerConnectClientThread> getHashMap() {
        return hashMap;
    }

    /**
     * 添加线程
     */
    public static void addServerConnectClientThread(String userID, ServerConnectClientThread scct) {
        hashMap.put(userID, scct);
    }
 
    /**
     * 根据用户的id获取对应的线程
     */
    public static ServerConnectClientThread getServerConnectClientThread(String userID) {
        return hashMap.get(userID);
    }

    /**
     * 获取在线用户列表
     */
    public static String getOnlineFriends() {
        Iterator<String> iterator = hashMap.keySet().iterator();
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
        hashMap.remove(userID);
    }
}