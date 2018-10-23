package com.itaiti.test.abstracts;

public class SayTest extends AbstractTest {
	
	@Override
	public void sayHello(String name){
		super.sayHello("111");
		System.out.println("Hello "+name);
	}
	
	public static void main(String[] args) {
		SayTest test = new SayTest();
		test.sayHello("Frank");
	}
}
