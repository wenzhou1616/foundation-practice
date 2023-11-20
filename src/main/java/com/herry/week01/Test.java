package com.herry.week01;

import com.herry.week01.dome.People;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author herry
 */
public class Test {
    public static void main(String[] args) {
//        int[] a = {1, 2, 3, 4, 5};
//        int[] b = new int[5];
//        System.arraycopy(a, 0, b, 0, 5);
//        System.out.println(b);
//        System.out.println(a);

//        People people1 = new People("张三");
//
//        List<People> sourceList = new ArrayList<>();
//        sourceList.add(people1);
//        List<People> newList = new ArrayList<>();
//        Collections.copy(newList, sourceList);
//        System.out.println(sourceList.toString()); // [People{name='张三'}]
//        System.out.println(newList.toString()); // [People{name='张三'}]
//
//        people1.setName("张三丰");
//        System.out.println(sourceList.toString()); // [People{name='张三丰'}]
//        System.out.println(newList.toString()); // [People{name='张三丰'}]

        People people1 = new People("张三");

        List<People> sourceList = new ArrayList<>();
        sourceList.add(people1);

        List<People> newList = new ArrayList<>(sourceList.size()); // 新集合的容量要大于等于原始集合
        Collections.copy(newList, sourceList);

        System.out.println(sourceList.toString()); // [People{name='张三'}]
        System.out.println(newList.toString()); // [People{name='张三'}]

        people1.setName("张三丰");
        System.out.println(sourceList.toString()); // [People{name='张三丰'}]
        System.out.println(newList.toString()); // [People{name='张三'}]


    }

}


