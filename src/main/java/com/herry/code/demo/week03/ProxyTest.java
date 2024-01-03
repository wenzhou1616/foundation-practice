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

//        Person student = new Student("张三");
//        JdkProxy proxy = new JdkProxy(student);
//        Person person = (Person) Proxy.newProxyInstance(student.getClass().getClassLoader(), student.getClass().getInterfaces(), proxy);
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

//        StudentMethodInterceptor studentMethodInterceptor = new StudentMethodInterceptor();
//        Tiger tiger = (Tiger) studentMethodInterceptor.getProxyInstance(new Tiger());
//        tiger.eat();
//        tiger.run();
    }
}
