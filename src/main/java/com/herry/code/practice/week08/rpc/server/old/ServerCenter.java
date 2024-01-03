package com.herry.code.practice.week08.rpc.server.old;

import java.io.IOException;

/**
 * 服务中心接口
 *
 * @author herry
 * @date 2024/1/3
 */
public interface ServerCenter {
    /**
     * 停止
     */
    void stop();

    /**
     * 开始
     * @throws IOException
     */
    void start() throws IOException;

    /**
     * 注册
     * @param serviceInterface
     * @param impl
     */
    void register(Class serviceInterface, Class impl);

    /**
     * 运行
     * @return
     */
    boolean isRunning();

    /**
     * 获取端口号
     * @return
     */
    int getPort();

}