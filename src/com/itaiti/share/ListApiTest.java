package com.itaiti.share;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * List 并集、交集、查集
 * 
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017年2月8日 下午5:01:31
 * @version V1.0
 */
public class ListApiTest {
	
	@Test
	public void test() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(1111);
		list1.add(2222);
		list1.add(3333);

		List<Integer> list2 = new ArrayList<>();
		list2.add(3333);
		list2.add(4444);
		list2.add(5555);

		// 并集
		// list1.addAll(list2);
		// 交集
		// list1.retainAll(list2);
		// 差集 removeAll和remove的区别
		list1.removeAll(list2);
		list1.remove(list2);
		// 无重复复并集
		//list2.removeAll(list1);
		//list1.addAll(list2);

		Iterator<Integer> it = list1.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());

		}
	}
}
