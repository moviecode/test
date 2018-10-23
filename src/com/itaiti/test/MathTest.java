package com.itaiti.test;

import org.junit.Test;

public class MathTest {
	
	@Test
	public void floorTest() {
		double d = Math.floor(1.1);
		System.out.println(d);
		double d2 = Math.floor(-1.1);
		System.out.println(d2);
	}
}
