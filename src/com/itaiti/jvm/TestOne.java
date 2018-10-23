package com.itaiti.jvm;

import org.junit.Test;

public class TestOne {
	private int count;
	public static int size;
	public final static int num = 100;
	public final int len = 1;
	
	static {
		System.out.println("TestOne静态初始化块执行了！");
	}
	
	@Test
	public void test() {
		int i = 100;
		System.out.println(i);
		System.out.println(count);
		// len = 200;
	}
	
	/*@Test
	public void testInteger() {
		// -128~127 走缓存，可以使用==比较， 超过这个范围使用compareTo比较
		Integer a = -129;
		Integer b = -129;
		System.out.println(a.compareTo(b));
	}*/
}
