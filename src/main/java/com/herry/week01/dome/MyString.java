package com.herry.week01.dome;

import java.util.Arrays;

/**
 * @author herry
 */
public class MyString {
    private final char[] data;

    public MyString(String str) {
        this.data = str.toCharArray();
    }

    /**
     * 获取字符串的长度
     */
    public int length() {
        return data.length;
    }

    /**
     * 根据索引获取字符串中的字符
     */
    public char charAt(int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return data[index];
    }

    /**
     * 字符串拼接，拼接到末尾
     */
    public MyString concat(MyString str) {
        // 创建一个新数组，然后将原来的数组和新的数组复制进去
        char[] newData = new char[data.length + str.length()];
        System.arraycopy(data, 0, newData, 0, data.length);
        System.arraycopy(str.data, 0, newData, data.length, str.length());
        return new MyString(new String(newData));
    }

    /**
     * 在原来的串中，查询子串第一次出现的位置
     */
    public int indexOf(MyString str) {
        if (str == null) {
            return -1;
        }

        char[] target = str.data;
        int targetLen = target.length;
        int limit = data.length - targetLen;

        // limit 之后的不用遍历了，因为剩余的原数组的长度小于子串，所以不存在子串
        for (int i = 0; i <= limit; i++) {
            // 将原串和子串依次比较
            int j = 0;
            while (j < targetLen && data[i + j] == target[j]) {
                j++;
            }

            // 若 j 等于 targetLen 说明子串在原串中全部匹配到了
            if (j == targetLen) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 字符串反转
     */
    public MyString reverse() {
        // 创建一个新数组，然后从末尾到开头依次复制进去
        char[] newData = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[data.length - 1 - i];
        }
        return new MyString(new String(newData));
    }

    /**
     * 子串提取，左闭右开，不包含结束索引的字符
     */
    public MyString substring(int beginIndex, int endIndex) {
        if (beginIndex < 0 || beginIndex > endIndex || endIndex > data.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        // 创建一个新数组，然后根据子串的开始和结束索引复制进新数组
        char[] newData = new char[endIndex - beginIndex];
        System.arraycopy(data, beginIndex, newData, 0, endIndex - beginIndex);
        return new MyString(new String(newData));
    }

    /**
     * 字符串比较
     */
    public boolean equals(MyString str) {
        // 先判断是否为null，长度是否相同
        if (str == null || data.length != str.length()) {
            return false;
        }
        // 循环，依次比较字符
        for (int i = 0; i < data.length; i++) {
            if (data[i] != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return new String(data);
    }

    public static void main(String[] args) {
        MyString myString = new MyString("Hello, World!");
        System.out.println(myString.length());
        System.out.println(myString.charAt(1));
        System.out.println(myString.concat(new MyString("abc")));
        System.out.println(myString.indexOf(new MyString("e")));
        System.out.println(myString.reverse());
        System.out.println(myString.substring(1, 3).toString());
        System.out.println(myString.equals(new MyString("Hello, World!")));
        System.out.println(myString.equals(new MyString("ab")));
    }
}