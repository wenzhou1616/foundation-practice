package com.herry.code.dome.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author herry
 */
public class StudentInvocationHandler implements InvocationHandler {

    private Object target;

    public Object getProxyInstance(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        if (name.equals("wakeup")) {
            System.out.println("醒了");
        } else if (name.equals("sleep")) {
            System.out.println("睡了");
        }
        return method.invoke(target, args);
    }
}
