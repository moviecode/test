package com.itaiti.test;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalTest {

	@Test
	public void test() {
		BigDecimal bd = new BigDecimal(1.22);
		System.out.println(bd);
		BigDecimal bd2 = new BigDecimal("1.22");
		System.out.println(bd2);
	}
	
	@Test
	public void addTest() {
		BigDecimal a = new BigDecimal("1.22");
		System.out.println("construct with a String value: " + a);
		BigDecimal b = new BigDecimal("2.22");
		BigDecimal c = a.add(b);
		System.out.println("aplus b is : " + c);
		
	}
}
