package com.itaiti.thread;

public class Demo {

	private static int count = 0;

	public static void add() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
	}

	// javap -c Demo
	// 脏读
	public void addTest() {
		for(int i=0; i<1000; i++) {
			new Thread(Demo::add).start();	//jdk8新特性，静态引用
		}

		try {
			Thread.sleep(10 * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("result:"+count);
	}

	public static void main(String[] args) {
//		addTest();
	}

}

class User {
	private String name;
	private String address;

}
