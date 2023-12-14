package com.herry.code;

import org.junit.Test;

import java.net.*;

/**
 * @author herry
 */
public class DomeTest {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/examples/myTest.txt");
        System.out.println("getProtocol() :"+url.getProtocol());
        System.out.println("getHost() :"+url.getHost());
        System.out.println("getPort() :"+url.getPort());
        System.out.println("getPath() :"+url.getPath());
        System.out.println("getFile() :"+url.getFile());
        System.out.println("getQuery() :"+url.getQuery());
    }

}


