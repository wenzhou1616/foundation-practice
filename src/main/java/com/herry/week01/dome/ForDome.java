package com.herry.week01.dome;

/**
 * @author herry
 */
public class ForDome {
    public static void main(String[] args) {
        // 求偶数和：求1-100之间的偶数和，并把求和结果在控制台输出
//        int sum = 0;
//        for (int i = 1; i <= 100; i++) {
//            if (i % 2 == 0) {
//                sum = sum + i;
//            }
//        }
//        System.out.println("100以内偶数和：" + sum);

        // 吃包子：1-5个包子，吃到第三个包子时停止
//        for (int i = 1; i <= 5; i++) {
//            System.out.println("正在吃第" + i + "个包子");
//            if (i == 3) {
//                System.out.println("不吃了");
//                break;
//            }
//        }

        // 吃包子：1-5个包子，第三个包子有虫不吃第三个
//        for (int i = 1; i <= 5; i++) {
//            if (i == 3) {
//                System.out.println("第三个包子有虫不吃");
//                continue;
//            }
//            System.out.println("正在吃第" + i + "个包子");
//        }

        // 100个[0,10)的随机数
//        Random r = new Random();
//        for (int i = 0; i < 100; i++) {
//            int num = r.nextInt(10);
//            System.out.println("随机数：" + num);
//        }

        /*逢7过
        游戏规则：从任意一个数字开始报数，当你要报的数字是包含7或者是7的倍数时都要说过：过
        需求：使用程序在控制台打印出1-100之间的满足逢七必过规则的数据*/
        // 包含7：个位是7，10位是7
        // 7的倍数：模以7等于0
//        for (int i = 1; i <= 100; i++) {
//            if (i % 10 == 7 || i / 10 % 10 == 7 || i % 7 == 0) {
//                System.out.print("过 ");
//                continue;
//            }
//            System.out.print(i + " ");
//        }

        // 求平方根：键盘录入一个大于等于2的整数 x ，计算并返回 x 的 平方根 。结果只保留整数部分 ，小数部分将被舍去
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入数字");
//        int x = sc.nextInt();
//        if (x < 2) {
//            System.out.println("数字不合法");
//        }
//        for (int i = 1; i <= x; i++) {
//            if (i * i == x) {
//                System.out.println("平方根是" + i);
//                break;
//            } else if (i * i > x) {
//                System.out.println("平方根是" + (i - 1));
//                break;
//            }
//        }

        // 判断是否为质数：键盘录入一个正整数 x ，判断该整数是否为一个质数。
        // 分析：如果在2 到 x - 1之间有数可以整除x，则x就是质数
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入整数：");
//        int x = sc.nextInt();
//        // 默认为true，表示是质数
//        boolean flag = true;
//        for (int i = 2; i < x - 1; i++) {
//            // 能被其中一个数整除则不是质数
//            if (x % i == 0){
//                flag = false;
//                break;
//            }
//        }
//        if (flag){
//            System.out.println("是质数");
//        }else {
//            System.out.println("不是质数");
//        }
    }
}
