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
		// 쳲�����ɢ�з�	Fibonacci
		int[] arr = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233,377, 610, 987, 1597, 2584, 4181, 6765, 10946};
		for(int v : arr) {
			long i = (v * 2654435769L) >> 28;
			System.out.println(v +"--"+ i+"--"+(i % 16));
		}
	}
	
	@Test
	public void modTest() {
		/*
		X % 2^n = X & (2^n �C 1)
		2^n��ʾ2��n�η���Ҳ����˵��һ������2^nȡģ == һ������(2^n �C 1)����λ������ ��
		����nΪ3����2^3 = 8����ʾ��2���ƾ���1000��2^3 = 7 ����0111��
		��ʱX & (2^3 �C 1) ���൱��ȡX��2���Ƶ������λ����
		��2���ƽǶ�������X / 8�൱�� X >> 3������X����3λ����ʱ�õ���X / 8���̣������Ƶ��Ĳ���(����λ)������X % 8��Ҳ����������
		*/
		// λ����(&)Ч��Ҫ�ȴ���ȡģ����(%)�ߺܶ࣬��Ҫԭ����λ����ֱ�Ӷ��ڴ����ݽ��в���������Ҫת��ʮ���ƣ���˴����ٶȷǳ��졣
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
