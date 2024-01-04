package com.herry.code.practice.week08.rpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息
 *
 * @author herry
 * @date 2024/1/3
 */
@Data
public class Message implements Serializable {
    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数
     */
    private Object[] args;
}
