package com.herry.code.demo.week03;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态代理
 *
 * @author herry
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public Object getProxyInstance(Object target){
        this.target = target;
        return Enhancer.create(target.getClass(), this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String name = method.getName();
        if ("wakeup".equals(name)) {
            System.out.println("醒了");
        } else if ("sleep".equals(name)) {
            System.out.println("睡了");
        }
        return method.invoke(target, objects);
    }
}
