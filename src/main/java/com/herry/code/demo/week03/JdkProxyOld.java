package com.herry.code.demo.week03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author herry
 */
public class JdkProxyOld implements InvocationHandler {

    private Object bean;

    public JdkProxyOld(Object bean) {
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
