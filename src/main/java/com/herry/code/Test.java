package com.herry.code;

import com.herry.code.dome.FileDome;
import com.herry.code.dome.Logger;
import com.herry.code.dome.People;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author herry
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Class<?> clazz = Class.forName("com.herry.code.dome.People");
//        Object obj = clazz.newInstance();
//        System.out.println(obj);

        Class<?> clazz = Class.forName("com.herry.code.dome.People");
        String name = clazz.getName();
        System.out.println(name);


    }
}


