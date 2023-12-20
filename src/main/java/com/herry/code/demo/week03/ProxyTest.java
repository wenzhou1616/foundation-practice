package com.herry.code.demo.week03;

import com.herry.code.demo.week02.week01.Tiger;

/**
 * @author herry
 */
public class ProxyTest {
    public static void main(String[] args) {
//        JdkProxy proxy = new JdkProxy(new Student("张三"));
//        Person person = (Person) Proxy.newProxyInstance(proxy.getClass().getClassLoader(), new Class[]{Person.class}, proxy);
//        person.sleep();
//        person.wakeup();
//
//        StudentInvocationHandler studentInvocationHandler = new StudentInvocationHandler();
//        Person jack = (Person) studentInvocationHandler.getProxyInstance(new Student("jack"));
//        jack.sleep();
//        jack.wakeup();

//        StudentMethodInterceptor studentMethodInterceptor = new StudentMethodInterceptor();
//        Student kay = (Student) studentMethodInterceptor.getProxyInstance(new Student("Kay"));
//        kay.wakeup();
//        kay.sleep();

        StudentMethodInterceptor studentMethodInterceptor = new StudentMethodInterceptor();
        Tiger tiger = (Tiger) studentMethodInterceptor.getProxyInstance(new Tiger());
        tiger.eat();
        tiger.run();
    }
}