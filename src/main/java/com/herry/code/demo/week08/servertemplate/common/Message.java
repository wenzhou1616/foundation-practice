package com.herry.code.demo.week08.servertemplate.common;

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
     * 第一个属性
     */
    private String attribute01;

    /**
     * 第二个属性
     */
    private String attribute02;

}
