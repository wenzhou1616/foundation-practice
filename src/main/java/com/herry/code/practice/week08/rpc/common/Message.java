package com.herry.code.practice.week08.rpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author herry
 * @date 2024/1/3
 */
@Data
public class Message implements Serializable {
    private String className;

    private String methodName;

    private Class[] parameterTypes;

    private Object[] args;
}
