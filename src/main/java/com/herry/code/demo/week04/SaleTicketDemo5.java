package com.herry.code.demo.week04;

public class SaleTicketDemo5 {
    public static void main(String[] args) {
        // 创建资源对象
        Ticket ticket = new Ticket();

        // 启动多个线程操作资源类的对象
        Thread t1 = new Thread("窗口一") {
            @Override
            public void run() {
                while (true) {
                    synchronized (ticket) {
                        ticket.sale();
                    }
                }
            }
        };
        Thread t2 = new Thread("窗口二") {
            @Override
            public void run() {
                while (true) {
                    synchronized (ticket) {
                        ticket.sale();
                    }
                }
            }
        };
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (ticket) {
                        ticket.sale();
                    }
                }
            }
        }, "窗口三");


        t1.start();
        t2.start();
        t3.start();
    }
}

class Ticket {
    private int ticket = 1000;

    public void sale() {
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出一张票，票号:" + ticket);
            ticket--;
        } else {
            System.out.println("票卖完了");
        }
    }
}