package com.herry.week01;

import sun.rmi.runtime.Log;

import java.util.*;

/**
 * @author herry
 */
public class ArrayDome {
    public static void main(String[] args) {
        // 数组求和
//        int[] arr = {1, 2, 3, 4, 5};
//        int sum = 0;
//        for (int j : arr) {
//            sum = sum + j;
//        }
//        System.out.println("数组求和的值是：" + sum);

        // 数组存储1,2,3,4,5,6,7,8,9,10，遍历数组得到每一个元素，统计数组里面一共有多少个能被3整除的数字
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        for (int i : arr) {
//            if (i % 3 == 0) {
//                System.out.println(i + "能被3整除");
//            }
//        }

        // 存储1,2,3,4,5,6,7,8,9,10
        // 如果是奇数，则将当前数字扩大两倍
        // 如果是偶数，则将当前数字变成二分之一
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] % 2 == 0){
//                arr[i] = arr[i] / 2;
//            }else {
//                arr[i] = arr[i] * 2;
//            }
//        }
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }

        // 求最值 33,5,22,44,55
//        int[] arr = {33, 5, 44, 55};
//        int max = arr[0];
//        int min = arr[0];
//        for (int i = 1; i < arr.length; i++) {
//            if (max < arr[i]){
//                max = arr[i];
//            }
//        }
//        System.out.println("最大值为：" + max);

//        生成10个1~100之间的随机数存入数组
//        1）求出所有数据的和
//        2）求所有数据的平均数
//        3）统计有多少个数据比平均值小
//        Random r = new Random();
//        int[] arr = new int[10];
//        for (int i = 0; i < 10; i++) {
//            arr[i] = r.nextInt(100) + 1;
//        }
//        int sum = arr[0];
//        for (int i = 1; i < arr.length; i++) {
//            sum = sum + arr[i];
//        }
//        int avg = sum / arr.length;
//        int count = 0;
//        for (int i : arr) {
//            if (i < avg) {
//                count++;
//            }
//        }
//        System.out.println("数据和：" + sum);
//        System.out.println("平均值：" + avg);
//        System.out.println("比平均值小的个数：" + count);

//        首位交换数据 1,2,3,4,5
//        int[] arr = {1, 2, 3, 4, 5};
//        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
//            int temp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = temp;
//        }
//        System.out.println(Arrays.toString(arr));

        // 打乱数据 1 - 5
        // 数组初始化，定义随机类

        // 打印101 - 200 之间的素数
        // 已知信息：素数就是质数，只有1和他本身两个因数的数是质数，因数是能整除他本身的数
        // 分析：质数是只能被1和他本身整除，2 到 他本身-1 的数都不能整除
        
        // 循环 101 - 200
        // 默认为true，表示默认为素数
//        boolean flag = true;
//        for (int i = 101; i <= 200; i++) {
//            // 循环内 再循环2到本身-1，判断是否整除，如果都不能整除则就是质数，然后打印
//            for (int j = 2; j < i; j++) {
//                // 只要有一个数能整除就不是素数，因此结束循环
//                if (i % j == 0){
//                    flag = false;
//                    break;
//                }
//            }
//            if (flag) {
//                System.out.println(i + "是素数");
//            }
//            // 恢复为true，表示是素数
//            flag = true;
//        }

        for (int i = 101; i <= 200; i++) {
            // 写在循环里面，就不用恢复现场了
            boolean flag = true;
            // 循环内 再循环2到本身-1，判断是否整除，如果都不能整除则就是质数，然后打印
            for (int j = 2; j < i; j++) {
                // 只要有一个数能整除就不是素数，因此结束循环
                if (i % j == 0){
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(i + "是素数");
            }
        }



    }
}



