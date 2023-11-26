package com.herry.code;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
//        System.out.println(people); //com.herry.code.dome.People@1540e19d
//        System.out.println(people1); //地址相同：com.herry.code.dome.People@1540e19d

//        People people = new People("sss",55);
//        People people1 = people;
//        System.out.println(people); //com.herry.code.dome.People@1540e19d
//        System.out.println(people1); //地址相同：com.herry.code.dome.People@1540e19d

//        People people = new People("sss", 55, new Work("jc"));
//        People people1 = (People) people.clone();
////        System.out.println(people); //com.herry.code.dome.People@1540e19d
////        System.out.println(people1); //浅拷贝，但是地址不相同，即创建了一个新的内容共享的对象：com.herry.code.dome.People@677327b6
//
//        people.setName("kk");
//        people.setAge(66);
//        people.a[0] = 4;
//
//        System.out.println(people);
//        System.out.println(people1);

//        People people1 = new People("张三");
//        List<People> sourceList = new ArrayList<>();
//        sourceList.add(people1);
//        List<People> newList = deepCopy(sourceList);
//        people1.setName("aa");
//        System.out.println(sourceList.toString()); // [People{name='aa'}]
//        System.out.println(newList.toString()); // [People{name='张三'}]

//        HashMap<Person, String> map = new HashMap<>();
//
//        Person person1 = new Person("Alice", 25);
//        Person person2 = new Person("Bob", 30);
//
//        map.put(person1, "Value 1");
//        map.put(person2, "Value 2");
//
//        // 使用自定义对象作为键进行检索
//        Person searchKey = new Person("Alice", 25);
//        String value = map.get(searchKey);
//        System.out.println(value);  // 输出 "Value 1"

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
//        System.out.println("增强for循环遍历集合:");
//        for (Integer i : list) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//
//        System.out.println("增强for循环遍历数组:");
//        int[] arr = {1, 2, 3, 4, 5, 6};
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }

//        List<String> fruits = new ArrayList<>();
//        fruits.add("apple");
//        fruits.add("banana");
//        fruits.add("orange");
//
//        fruits.forEach(fruit -> System.out.println(fruit));

        // 筛选偶数
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        List<Integer> evenNumbers = numbers.stream()
//                                           .filter(n -> n % 2 == 0)
//                                           .collect(Collectors.toList());
//        System.out.println(evenNumbers); // 输出：[2, 4, 6, 8, 10]

//        List<Integer> list = Arrays.asList(3, 1, 4, 2, 5);
//        List<Integer> result = list.stream().sorted().collect(Collectors.toList());
//        System.out.println(result);


    }



}


