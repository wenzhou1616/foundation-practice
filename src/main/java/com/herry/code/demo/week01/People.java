package com.herry.code.demo.week01;

import java.io.Serializable;

/**
 * @author herry
 */
public class People implements Cloneable, Serializable {
    private String name;
    private int age;
    private Work work;

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public People(String name, int age, Work work) {
        this.name = name;
        this.age = age;
        this.work = work;
    }

    public People(Work work) {
        this.work = work;
    }

    public People(int age, Work work) {
        this.age = age;
        this.work = work;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", work=" + work;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public People(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
