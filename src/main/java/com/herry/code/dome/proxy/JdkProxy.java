package com.herry.code.dome.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author herry
 */
public class JdkProxy implements InvocationHandler {

    private Object bean;

    public JdkProxy(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        if (name.equals("wakeup")) {
            System.out.println("早安");
        } else if (name.equals("sleep")){
            System.out.println("晚安");
        }

        return method.invoke(bean, args);
    }
}
