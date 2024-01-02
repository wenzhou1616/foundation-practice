package com.herry.code.practice.week07.chatsystem.common;
 
/**
 * 消息类型常量
 *
 * @author herry
 * @date 2023/12/26
 */
public interface MessageType {
    /**
     * 登录成功消息
     */
    String MESSAGE_LOGIN_SUCCESS = "1";

    /**
     * 登录失败消息
     */
    String MESSAGE_LOGIN_FAIL = "0";

    /**
     * 普通消息
     */
    String MESSAGE_COMMON_MES = "2";

    /**
     * 群发消息
     */
    String MESSAGE_COMMON_MES_ALL = "6";

    /**
     * 请求拉取在线用户的列表消息
     */
    String MESSAGE_GET_ONLINE_FRIENDS = "3";

    /**
     * 返回在线用户的列表消息
     */
    String MESSAGE_RETURN_ONLINE_FRIENDS = "4";

    /**
     * 表示客户端请求退出系统消息
     */
    String MESSAGE_CLIENT_EXIT = "5";

    /**
     * 文件传输消息
     */
    String MESSAGE_FILE_TRANSMISSION = "8";
}
