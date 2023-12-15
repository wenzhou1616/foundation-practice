package com.herry.code.demo.week05;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
    @Test
    public void test01() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
    }

    @Test
    public void test02()throws UnknownHostException{
        InetAddress baidu = InetAddress.getByName("www.baidu.com");
        System.out.println(baidu);
    }

    @Test
    public void test03()throws UnknownHostException{
//		byte[] addr = {112,54,108,98};
        byte[] addr = {(byte)192,(byte)168,24,56};
        InetAddress baidu = InetAddress.getByAddress(addr);
        System.out.println(baidu);

    }
}