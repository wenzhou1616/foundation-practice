package com.herry.code.demo.week03;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author herry
 */
public class StudentMethodInterceptor implements MethodInterceptor {

    private Object target;

    public Object getProxyInstance(Object target){
        this.target = target;
        return Enhancer.create(target.getClass(), this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String name = method.getName();
//        if (name.equals("wakeup")) {
//            System.out.println("醒了");
//        } else if (name.equals("sleep")) {
//            System.out.println("睡了");
//        }
        System.out.println("hello");
        return method.invoke(target, objects);
    }
}
