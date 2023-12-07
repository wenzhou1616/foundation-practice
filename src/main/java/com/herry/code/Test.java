package com.herry.code;

import com.herry.code.dome.MyThread;
import java.*;

/**
 * @author herry
 */
public class Test {
    public static void main(String[] args) {
        Thread t = new MyThread();
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(t.getState());
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(t.getState());
        }

    }
}


