package com.itaiti.jvm;

import org.junit.Test;

public class TestOne {
	private int count;
	public static int size;
	public final static int num = 100;
	public final int len = 1;
	
	static {
		System.out.println("TestOne��̬��ʼ����ִ���ˣ�");
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
		// -128~127 �߻��棬����ʹ��==�Ƚϣ� ���������Χʹ��compareTo�Ƚ�
		Integer a = -129;
		Integer b = -129;
		System.out.println(a.compareTo(b));
	}*/
}
