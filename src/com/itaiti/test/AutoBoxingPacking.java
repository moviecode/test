package com.itaiti.test;

import org.junit.Test;

/**
 * Java 自动装箱拆箱 参考：http://blog.csdn.net/jackiehff/article/details/8509056
 * 
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017年8月29日 上午10:53:59
 * @version V1.0
 */
public class AutoBoxingPacking {

	/*
	 * Java 1.5中引入了自动装箱和拆箱机制：
	 * (1)自动装箱：把基本类型用它们对应的引用类型包装起来，使它们具有对象的特质，可以调用toString()、hashCode()、getClass
	 * ()、equals()等方法。 如下： Integer a=3;//这是自动装箱 其实编译器调用的是static Integer
	 * valueOf(int i)这个方法,valueOf(int i)返回一个表示指定int值的Integer对象,那么就变成这样: Integer
	 * a=3; => Integer a=Integer.valueOf(3);
	 * (2)拆箱：跟自动装箱的方向相反，将Integer及Double这样的引用类型的对象重新简化为基本类型的数据。 如下： int i = new
	 * Integer(2);//这是拆箱 编译器内部会调用int intValue()返回该Integer对象的int值
	 */
	@Test
	public void test() {
		Integer integer1 = 100;
		Integer integer2 = 100;
		System.out.println("integer1==integer2: " + (integer1 == integer2));// true
																			// 自动装箱的两个缓存中的
																			// Integer对象的引用比较
		System.out.println("integer1.equals(integer2): " + (integer1.equals(integer2)));// true
		System.out.println("integer1.compare(integer2): " + integer1.compareTo(integer2));// 0:相等
																							// 1:大于
																							// -1:小于

		// -128~127是从缓存中去，超过这个范围就会重新new一个
		// XX:AutoBoxCacheMax=256 设置最大值，最小值-128不能修改
		Integer integer3 = 200;
		Integer integer4 = 200;
		System.out.println("integer3==integer4: " + (integer3 == integer4));// false
																			// 自动装箱的两个new
																			// Integer的引用比较
		System.out.println("integer3>integer4: " + (integer3 > integer4)); // false
																			// 将两个对象拆箱，再比较大小
		System.out.println("integer3.equals(integer4): " + (integer3.equals(integer4)));// true
		System.out.println("integer3.compare(integer4): " + integer3.compareTo(integer4));// 0
		Integer integer5 = new Integer(100);
		Integer integer6 = new Integer(100);
		System.out.println("integer5==integer6: " + (integer5 == integer6)); // false
																				// 两个不同的Integer对象引用的比较
		System.out.println("integer5.equals(integer6): " + (integer5.equals(integer6)));// true
		System.out.println("integer5.compare(integer6): " + integer5.compareTo(integer6));// 0
		int int1 = 100;
		System.out.println("integer1==int1: " + (integer1 == int1));// true
																	// Integer缓存对象拆箱后与int比较
		System.out.println("integer1.equals(int1): " + (integer1.equals(int1)));// true
		System.out.println("integer1.compare(int1): " + integer1.compareTo(int1));// 0
		int int2 = 200;
		System.out.println("integer3==int2: " + (integer3 == int2));// true
																	// Integer对象拆箱后与int比较
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
	 * 源码分析 valueOf工厂方法
	 * 
	 * @param i
	 * @return
	 */
	/*
	 * public static Integer valueOf(int i) { if(i >= -128 &&i
	 * <=IntegerCache.high) //如果i在-128~high之间,就直接在缓存中取出i的Integer类型对象 return
	 * IntegerCache.cache[i + 128]; else return new Integer(i); //否则就在堆内存中创建 }
	 */
}
