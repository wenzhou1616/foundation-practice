package com.herry.code.dome.proxy;

import com.herry.code.dome.proxy.Person;

public class Student implements Person {

    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }


    @Override
    public void wakeup() {
        System.out.println(name + "醒了");
    }

    @Override
    public void sleep() {
        System.out.println(name + "睡着了");
    }
}