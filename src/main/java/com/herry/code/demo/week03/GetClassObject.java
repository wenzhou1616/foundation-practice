package com.herry.code.demo.week03;

import org.junit.Test;

public class GetClassObject {
    /**
     * 方法一
     */
    @Test
    public void test01() throws ClassNotFoundException {
        Class c1 = GetClassObject.class;

        GetClassObject obj = new GetClassObject();
        Class c2 = obj.getClass();

        Class c3 = Class.forName("com.herry.code.demo.week03.GetClassObject");
        Class c4 = ClassLoader.getSystemClassLoader().loadClass("com.herry.code.demo.week03.GetClassObject");

        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);
        System.out.println("c4 = " + c4);

        System.out.println(c1 == c2); // true
        System.out.println(c1 == c3); // true
        System.out.println(c1 == c4); // true
    }
}