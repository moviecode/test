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
	 * Predicate ν�ʸ���ʹ��
	 */
	@Test
	public void predicateDemo() {
		// ��ƻ��
		Predicate<Apple> redApple = a -> "red".equalsIgnoreCase(a.getColor());
		System.out.println("��ƻ��:"+filter(appleList, redApple));
		// �Ǻ�ƻ��
		Predicate<Apple> netRedApple = redApple.negate();
		System.out.println("�Ǻ�ƻ��:"+filter(appleList, netRedApple));
		// ����150g�ĺ�ƻ��
		Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
		System.out.println("����150g�ĺ�ƻ��:"+filter(appleList, redAndHeavyApple));
		// ����150g�ĺ�ƻ�� �� ��ƻ��
		Predicate<Apple> redAndHeavyAppleOrGreen = 
				redApple.and(a -> a.getWeight()>150)
				.or(a -> "green".equalsIgnoreCase(a.getColor()));
		System.out.println("����150g�ĺ�ƻ�� �� ��ƻ��:"+filter(appleList, redAndHeavyAppleOrGreen));
	}
	
	/**
	 * �Ƚ�������
	 */
	@Test
	public void comparatorDemo() {
		Comparator<Apple> c = Comparator.comparingInt(Apple::getWeight);
		// ������������
		appleList.sort(c);
		System.out.println("������������:"+appleList);
		// ������������
		Collections.shuffle(appleList);
		appleList.sort(c.reversed());
		System.out.println("������������:"+appleList);
		// �Ȱ����������ٰ���ɫ����
		Collections.shuffle(appleList);
		appleList.sort(c.reversed().thenComparing(Apple::getColor));
		System.out.println("�Ȱ����������ٰ���ɫ����:"+appleList);
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
