package com.herry.code.practice.week07.chatsystem.common;
 
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author herry
 * @date 2023/12/26
 */
@Data
public class User implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 密码
     */
    private String pwd;

    private static final long serialVersionUID = 1L;

    public User() {}
    public User(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}
