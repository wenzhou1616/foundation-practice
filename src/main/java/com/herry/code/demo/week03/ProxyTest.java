package com.herry.code.demo.week03;

/**
 * @author herry
 */
public class ProxyTest {
    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        Person student01Proxy = (Person) jdkProxy.getProxyInstance(new Student("张三"));
        student01Proxy.sleep();

        CglibProxy cglibProxy = new CglibProxy();
        Person student02Proxy = (Person) cglibProxy.getProxyInstance(new Student("李四"));
        student02Proxy.sleep();

    }
}
