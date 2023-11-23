package com.herry.week01;

import com.herry.week01.dome.People;
import com.herry.week01.dome.Work;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author herry
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
//        int[] a = {1, 2, 3, 4, 5};
//        int[] b = new int[5];
//        System.arraycopy(a, 0, b, 0, 5);
//        System.out.println(b);
//        System.out.println(a);

//
//        people1.setName("张三丰");
//        System.out.println(sourceList.toString()); // [People{name='张三丰'}]
//        System.out.println(newList.toString()); // [People{name='张三丰'}]

//        People people1 = new People("张三");
//
//        List<People> sourceList = new ArrayList<>();
//        sourceList.add(people1);
//
//        List<People> newList = new ArrayList<>(sourceList.size()); // 新集合的容量要大于等于原始集合
//        Collections.copy(newList, sourceList);
//
//        System.out.println(sourceList.toString()); // [People{name='张三'}]
//        System.out.println(newList.toString()); // [People{name='张三'}]
//
//        people1.setName("张三丰");
//        System.out.println(sourceList.toString()); // [People{name='张三丰'}]
//        System.out.println(newList.toString()); // [People{name='张三'}]

//        People people1 = new People("tom",18);
//        People people2 = people1;
//        people1.setAge(66);
//        System.out.println(people1);
//        System.out.println(people2);

//        People people2 = new People("jack",22);
//
//        List<People> list = new ArrayList<>();
//        list.add(people1);
//
//        List<People> list1 = new ArrayList<>(list);
//        System.out.println("修改前：");
//        System.out.println("原数组：" + list.toString());
//        System.out.println("新数组：" + list1.toString());
//
////
////        people1.setName("xx");
////        people1.setAge(66);
//
//        list.get(0).setName("ss");
//        list.get(0).setAge(99);
//
//        System.out.println("修改后：");
//        System.out.println("原数组：" + list.toString());
//        System.out.println("新数组：" + list1.toString());

        // 基本数据类型只有深拷贝
//        int a = 1;
//        int b = a;
//        b = 3;
//
//        System.out.println(a);
//        System.out.println(b);

//        People people = new People("sss",55);
//        People people1 = people;
//        System.out.println(people); //com.herry.week01.dome.People@1540e19d
//        System.out.println(people1); //地址相同：com.herry.week01.dome.People@1540e19d

//        People people = new People("sss",55);
//        People people1 = people;
//        System.out.println(people); //com.herry.week01.dome.People@1540e19d
//        System.out.println(people1); //地址相同：com.herry.week01.dome.People@1540e19d

//        People people = new People("sss", 55, new Work("jc"));
//        People people1 = (People) people.clone();
////        System.out.println(people); //com.herry.week01.dome.People@1540e19d
////        System.out.println(people1); //浅拷贝，但是地址不相同，即创建了一个新的内容共享的对象：com.herry.week01.dome.People@677327b6
//
//        people.setName("kk");
//        people.setAge(66);
//        people.a[0] = 4;
//
//        System.out.println(people);
//        System.out.println(people1);

        People people1 = new People("张三");
        List<People> sourceList = new ArrayList<>();
        sourceList.add(people1);
        List<People> newList = deepCopy(sourceList);
        people1.setName("aa");
        System.out.println(sourceList.toString()); // [People{name='aa'}]
        System.out.println(newList.toString()); // [People{name='张三'}]


    }

    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

}


