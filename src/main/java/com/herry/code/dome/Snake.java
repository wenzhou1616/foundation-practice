package com.herry.code.dome;

public class Snake extends Animal {
    public String name = "snake";
    
    @Override
    public void eat() {
        System.out.println("蛇吃鼠！");
    }

	@Override
    public void run() {
		System.out.println("蛇爬行！");
	}
}