package com.herry.code.dome;

public class Tiger extends Animal {
    public String name = "tiger";

    @Override
    public void eat() {
        System.out.println("虎吃鸡！");
    }
    
    @Override
    public void run() {
		System.out.println("虎奔跑！");
	}
}