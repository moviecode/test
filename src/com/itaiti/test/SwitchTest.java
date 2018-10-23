package com.itaiti.test;

import java.util.HashMap;
import java.util.Hashtable;

import org.junit.Test;

import com.google.common.collect.Maps;

public class SwitchTest {
	
	@Test
	public void test() {
		switchCase(1);
		
	}
	public void switchCase(int type) {
		switch(type) {
			case 1:
			case 2:
				System.out.println(1);
				break;
			case 3:
				System.out.println(3);
				break;
			default:
				System.out.println(0);
		}
	}
	
	@Test
	public void hashTest() {
		// 斐波那契散列法	Fibonacci
		int[] arr = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233,377, 610, 987, 1597, 2584, 4181, 6765, 10946};
		for(int v : arr) {
			long i = (v * 2654435769L) >> 28;
			System.out.println(v +"--"+ i+"--"+(i % 16));
		}
	}
	
	@Test
	public void modTest() {
		/*
		X % 2^n = X & (2^n – 1)
		2^n表示2的n次方，也就是说，一个数对2^n取模 == 一个数和(2^n – 1)做按位与运算 。
		假设n为3，则2^3 = 8，表示成2进制就是1000。2^3 = 7 ，即0111。
		此时X & (2^3 – 1) 就相当于取X的2进制的最后三位数。
		从2进制角度来看，X / 8相当于 X >> 3，即把X右移3位，此时得到了X / 8的商，而被移掉的部分(后三位)，则是X % 8，也就是余数。
		*/
		// 位运算(&)效率要比代替取模运算(%)高很多，主要原因是位运算直接对内存数据进行操作，不需要转成十进制，因此处理速度非常快。
		int len = 16;
		System.out.println(2018 % len);
		System.out.println(2018 & (len -1));
		
		HashMap<String,Integer> map = Maps.newHashMap();
		System.out.println("putIfAbsent:" + map.putIfAbsent("a", 1));
		System.out.println("putIfAbsent:" + map.putIfAbsent("a", 2));
		System.out.println("putIfAbsent:" + map.get("a"));
		// Hashtable table = new Hashtable();
	}
	
}
