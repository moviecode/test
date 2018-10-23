package com.itaiti.test.abstracts;

public abstract class AbstractTest implements ITest{
	
	@Override
	public void sayHello(String name){
		System.out.println("Hello "+name);
	}
}
