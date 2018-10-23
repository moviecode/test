package com.itaiti.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

import com.google.common.collect.Lists;

public class PredicateTest {
	
	private static List<Apple> appleList;
	
	static {
		appleList = Lists.newArrayList();
		appleList.add(new Apple(80,"green"));
		appleList.add(new Apple(155, "green"));
		appleList.add(new Apple(120, "red"));
		appleList.add(new Apple(160, "red"));
		appleList.add(new Apple(80, "red"));
	}
	
	@Test
	public void demo() {
		Predicate<String> nonEmptyStringPredicate = (String s) -> null != s && !"".equals(s);
		List<String> list = Lists.newArrayList("a",null,"b","","c");
		List<String> nonEmpty = filter(list, nonEmptyStringPredicate);
		System.out.println(nonEmpty);
	}
	
	/**
	 * Predicate 谓词复合使用
	 */
	@Test
	public void predicateDemo() {
		// 红苹果
		Predicate<Apple> redApple = a -> "red".equalsIgnoreCase(a.getColor());
		System.out.println("红苹果:"+filter(appleList, redApple));
		// 非红苹果
		Predicate<Apple> netRedApple = redApple.negate();
		System.out.println("非红苹果:"+filter(appleList, netRedApple));
		// 大于150g的红苹果
		Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
		System.out.println("大于150g的红苹果:"+filter(appleList, redAndHeavyApple));
		// 大于150g的红苹果 或 绿苹果
		Predicate<Apple> redAndHeavyAppleOrGreen = 
				redApple.and(a -> a.getWeight()>150)
				.or(a -> "green".equalsIgnoreCase(a.getColor()));
		System.out.println("大于150g的红苹果 或 绿苹果:"+filter(appleList, redAndHeavyAppleOrGreen));
	}
	
	/**
	 * 比较器复合
	 */
	@Test
	public void comparatorDemo() {
		Comparator<Apple> c = Comparator.comparingInt(Apple::getWeight);
		// 重量升序排序
		appleList.sort(c);
		System.out.println("重量升序排序:"+appleList);
		// 重量降序排序
		Collections.shuffle(appleList);
		appleList.sort(c.reversed());
		System.out.println("重量降序排序:"+appleList);
		// 先按重量降序，再按颜色排序
		Collections.shuffle(appleList);
		appleList.sort(c.reversed().thenComparing(Apple::getColor));
		System.out.println("先按重量降序，再按颜色排序:"+appleList);
	}
	
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = Lists.newArrayList();
		list.forEach(v -> {
			if(p.test(v)) {
				results.add(v);
			}
		});
		return results;
	}
	
}
