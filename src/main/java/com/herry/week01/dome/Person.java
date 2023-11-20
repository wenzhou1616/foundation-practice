package com.herry.week01.dome;

import java.util.ArrayList;
import java.util.List;

public class Person implements Cloneable {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        List<Person> srcList = new ArrayList<>();
        srcList.add(new Person("Alice"));
        srcList.add(new Person("Bob"));

        List<Person> destList = new ArrayList<>(srcList.size());
        for (Person person : srcList) {
            destList.add((Person)person.clone());
        }

        // 修改源列表中第一个元素的姓名
        srcList.get(0).setName("Charlie");

        System.out.println("srcList: " + srcList);
        System.out.println("destList: " + destList);
    }
}

