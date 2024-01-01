package com.herry.code.practice.week07.chatsystem.common;
 
import lombok.Data;
import java.io.Serializable;

/**
 * 消息
 *
 * @author herry
 * @date 2023/12/26
 */
@Data
public class Message implements Serializable {
    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送时间
     */
    private String sender;

    /**
     * 接收者
     */
    private String receiver;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息类型
     */
    private String mesType;

    /**
     * 文件
     */
    private byte[] file;

    /**
     * 文件大小
     */
    private int fileLen;

    /**
     * 数据源文件路径
     */
    private String souPath;

    /**
     * 目的地文件路径
     */
    private String desPath;

    private static final long serialVersionUID = 1L;
}