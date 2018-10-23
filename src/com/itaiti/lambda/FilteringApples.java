package com.itaiti.lambda;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.Data;

public class FilteringApples {
	
	private static List<Apple> appleList;
	
	static {
		appleList = Lists.newArrayList();
		appleList.add(new Apple(80,"green"));
		appleList.add(new Apple(155, "green"));
		appleList.add(new Apple(120, "red"));
	}
	
	/**
	 * 逻辑判断
	 */
	@Test
	public void demo2() {
		System.out.println(filterGreenApples(appleList, "green"));
		System.out.println(filterApplesByWeight(appleList, 150));
	}
	
	
	/**
	 * 使用接口处理
	 */
	@Test
	public void demo() {
		List<Apple> heavyApples = filterApples(appleList, new AppleHeavyWeightPredicate());
		List<Apple> greenApples = filterApples(appleList, new AppleGreenColorPredicate());
		System.out.println(heavyApples);
		System.out.println(greenApples);
	}
	
	/**
	 * 匿名内部类
	 */
	@Test
	public void demo3() {
		List<Apple> heavyApples = filterApples(appleList, new ApplePredicate(){
			@Override
			public boolean test(Apple apple) {
				return apple.getWeight() > 150;
			}
		});
		System.out.println(heavyApples);
		
		List<Apple> greenApples = filterApples(appleList, new ApplePredicate() {
			@Override
			public boolean test(Apple apple) {
				return "green".equals(apple.getColor());
			}
		});
		System.out.println(greenApples);
	}
	
	@Test
	public void demo4() {
		Thread thread = new Thread(() -> System.out.println("Hello World!"));
		thread.start();
	}
	
	@Test
	public void demo5() {
		Comparator<Apple> byColor =
				(Apple a1, Apple a2) -> a1.getColor().compareTo(a2.getColor());
	}
	
	@Test
	public void demo6() {
		Runnable r = () -> System.out.println("running......");
		r.run();
		/*Thread thread = new Thread(r);
		thread.start();
		System.out.println("test.....");*/
	}
	
	public static List<Apple> filterApples(List<Apple> list, ApplePredicate applePredicate) {
		List<Apple> result = Lists.newArrayList();
		list.forEach(apple -> {
			if(applePredicate.test(apple)) {
				result.add(apple);
			}
		});
		return result;
	}
	
	public static List<Apple> filterGreenApples(List<Apple> list, String color) {
		List<Apple> result = Lists.newArrayList();
		list.forEach(apple -> {
			if(color.equals(apple.getColor())) {
				result.add(apple);
			}
		});
		return result;
	}
	
	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = Lists.newArrayList();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}
}

@Data
class Apple {
	private String color;
	private int weight;
	
	public Apple() {
		
	}
	
	public Apple(int weight, String color) {
		this.weight = weight;
		this.color = color;
	}
}