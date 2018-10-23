package com.itaiti.test;

import java.util.stream.IntStream;

public class StringTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test();
		//test2();
		// System.out.println("---"+((String)null));
		String s = "时光时光123,234,11,";
		System.out.println(s.substring(0, s.length()-1));
	}
	
	// == 和 equals方法测试
	public static void test(){
		String a = "aaa";
		String b = "aaa";
		String c = new String("aaa");
		String d = new String("aaa");
		System.out.println(a.equals(b));
		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(c == d);
		//Object obj = new Object();
	}
	
	//replace方法测试
	public static void test2(){
		/*String old = "abc123";
		System.out.println(old.replace("abc", "aaa"));
		System.out.println(old);
		System.out.println(old.replace('a', 'x'));
		System.out.println(old);*/
	}
	
}
