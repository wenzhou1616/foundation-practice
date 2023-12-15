package com.herry.code.demo.week02;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections4.CollectionUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author herry
 */
public class ArrayListDemo {
    public static void main(String[] args) {
//        People people1 = new People("tom",18);
//        List<People> list = new ArrayList<>();
//        list.add(people1);
//
//        List<People> list1 = new ArrayList<>(list);
//        System.out.println("原数组：" + list.toString());  // 原数组：[People{name='tom', age=18}]
//        System.out.println("新数组：" + list1.toString()); // 新数组：[People{name='tom', age=18}]

//        People people1 = new People("tom",18);
//        List<People> list = new ArrayList<>();
//        list.add(people1);
//        List<People> list1 = new ArrayList<>();
//        list1.addAll(list);
//        System.out.println("原数据：" + list.toString());  // 原数据：[People{name='tom', age=18}]
//        System.out.println("新数据：" + list1.toString()); // 新数据：[People{name='tom', age=18}]


//        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
//        List<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6));
//
//        list1.addAll(list2); // 将list2中的元素合并到list1中
//
//        System.out.println(list1); // 输出 [1, 2, 3, 4, 5, 6]

//        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
//        List<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6));
//
//        List<Integer> mergedList = Stream.concat(list1.stream(), list2.stream())
//                .collect(Collectors.toList());
//
//        System.out.println(mergedList); // 输出 [1, 2, 3, 4, 5, 6]

        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6));

        CollectionUtils.addAll(list1, list2);

        System.out.println(list1); // 输出 [1, 2, 3, 4, 5, 6]




    }

    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
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
