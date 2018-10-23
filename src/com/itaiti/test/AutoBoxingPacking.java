package com.itaiti.test;

import org.junit.Test;

/**
 * Java �Զ�װ����� �ο���http://blog.csdn.net/jackiehff/article/details/8509056
 * 
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017��8��29�� ����10:53:59
 * @version V1.0
 */
public class AutoBoxingPacking {

	/*
	 * Java 1.5���������Զ�װ��Ͳ�����ƣ�
	 * (1)�Զ�װ�䣺�ѻ������������Ƕ�Ӧ���������Ͱ�װ������ʹ���Ǿ��ж�������ʣ����Ե���toString()��hashCode()��getClass
	 * ()��equals()�ȷ����� ���£� Integer a=3;//�����Զ�װ�� ��ʵ���������õ���static Integer
	 * valueOf(int i)�������,valueOf(int i)����һ����ʾָ��intֵ��Integer����,��ô�ͱ������: Integer
	 * a=3; => Integer a=Integer.valueOf(3);
	 * (2)���䣺���Զ�װ��ķ����෴����Integer��Double�������������͵Ķ������¼�Ϊ�������͵����ݡ� ���£� int i = new
	 * Integer(2);//���ǲ��� �������ڲ������int intValue()���ظ�Integer�����intֵ
	 */
	@Test
	public void test() {
		Integer integer1 = 100;
		Integer integer2 = 100;
		System.out.println("integer1==integer2: " + (integer1 == integer2));// true
																			// �Զ�װ������������е�
																			// Integer��������ñȽ�
		System.out.println("integer1.equals(integer2): " + (integer1.equals(integer2)));// true
		System.out.println("integer1.compare(integer2): " + integer1.compareTo(integer2));// 0:���
																							// 1:����
																							// -1:С��

		// -128~127�Ǵӻ�����ȥ�����������Χ�ͻ�����newһ��
		// XX:AutoBoxCacheMax=256 �������ֵ����Сֵ-128�����޸�
		Integer integer3 = 200;
		Integer integer4 = 200;
		System.out.println("integer3==integer4: " + (integer3 == integer4));// false
																			// �Զ�װ�������new
																			// Integer�����ñȽ�
		System.out.println("integer3>integer4: " + (integer3 > integer4)); // false
																			// ������������䣬�ٱȽϴ�С
		System.out.println("integer3.equals(integer4): " + (integer3.equals(integer4)));// true
		System.out.println("integer3.compare(integer4): " + integer3.compareTo(integer4));// 0
		Integer integer5 = new Integer(100);
		Integer integer6 = new Integer(100);
		System.out.println("integer5==integer6: " + (integer5 == integer6)); // false
																				// ������ͬ��Integer�������õıȽ�
		System.out.println("integer5.equals(integer6): " + (integer5.equals(integer6)));// true
		System.out.println("integer5.compare(integer6): " + integer5.compareTo(integer6));// 0
		int int1 = 100;
		System.out.println("integer1==int1: " + (integer1 == int1));// true
																	// Integer�������������int�Ƚ�
		System.out.println("integer1.equals(int1): " + (integer1.equals(int1)));// true
		System.out.println("integer1.compare(int1): " + integer1.compareTo(int1));// 0
		int int2 = 200;
		System.out.println("integer3==int2: " + (integer3 == int2));// true
																	// Integer����������int�Ƚ�
		System.out.println("integer3.equals(int2): " + (integer3.equals(int2)));// true
		System.out.println("integer3.compare(int2): " + integer3.compareTo(int2));// 0
	}

	@Test
	public void test2() {
		System.out.println(parseInt("9", 10));
	}

	public static int parseInt(String s, int radix) throws NumberFormatException {
		/*
		 * WARNING: This method may be invoked early during VM initialization
		 * before IntegerCache is initialized. Care must be taken to not use the
		 * valueOf method.
		 */

		if (s == null) {
			throw new NumberFormatException("null");
		}

		if (radix < Character.MIN_RADIX) {
			throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
		}

		if (radix > Character.MAX_RADIX) {
			throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
		}

		int result = 0;
		boolean negative = false;
		int i = 0, len = s.length();
		int limit = -Integer.MAX_VALUE;
		int multmin;
		int digit;

		if (len > 0) {
			char firstChar = s.charAt(0);
			if (firstChar < '0') { // Possible leading "+" or "-"
				if (firstChar == '-') {
					negative = true;
					limit = Integer.MIN_VALUE;
				} else if (firstChar != '+')
					// throw NumberFormatException.forInputString(s);

					if (len == 1) // Cannot have lone "+" or "-"
						// throw NumberFormatException.forInputString(s);
						i++;
			}
			multmin = limit / radix;
			while (i < len) {
				// Accumulating negatively avoids surprises near MAX_VALUE
				digit = Character.digit(s.charAt(i++), radix);
				if (digit < 0) {
					// throw NumberFormatException.forInputString(s);
				}
				if (result < multmin) {
					// throw NumberFormatException.forInputString(s);
				}
				result *= radix;
				if (result < limit + digit) {
					// throw NumberFormatException.forInputString(s);
				}
				result -= digit;
			}
		} else {
			// throw NumberFormatException.forInputString(s);
		}
		return negative ? result : -result;
	}

	/**
	 * Դ����� valueOf��������
	 * 
	 * @param i
	 * @return
	 */
	/*
	 * public static Integer valueOf(int i) { if(i >= -128 &&i
	 * <=IntegerCache.high) //���i��-128~high֮��,��ֱ���ڻ�����ȡ��i��Integer���Ͷ��� return
	 * IntegerCache.cache[i + 128]; else return new Integer(i); //������ڶ��ڴ��д��� }
	 */
}
