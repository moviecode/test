package com.itaiti.share;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * ArrayList的三种遍历方式性能测试
 * 
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017年1月22日 上午11:44:07
 * @version V1.0
 */
public class ListTest {
	private List<String> list = new ArrayList<>();
	
	@Test
	public void test() {
		System.out.println(1111);
		foreach();
		forlist();
		iteratorList();
	}

	// list 集合中添加100万条数据
	@Before
	public void initList() {
		int i = 0;
		int num = 1000000;
		long start = System.currentTimeMillis();
		for (i = 0; i < num; i++) {
			list.add("list" + i);
		}
		long end = System.currentTimeMillis();
		long count = end - start;
		System.out.println("initList 时间" + count + "毫秒");
	}

	// list 集合遍历 foreach
	public void foreach() {
		long start = System.currentTimeMillis();
		String value = "";
		for (String data : list) {
			value = data;
		}

		long end = System.currentTimeMillis();
		long count = end - start;
		System.out.println("foreach 循环时间" + count + "毫秒");
	}

	// list集合遍历 for
	public void forlist() {
		long start = System.currentTimeMillis();
		int i = 0;
		String value = "";
		for (i = 0; i < list.size(); i++) {
			value = list.get(i);
		}
		long end = System.currentTimeMillis();
		long count = end - start;
		System.out.println("for list.size() 遍历时间" + count + "毫秒");
	}

	// Iterator 遍历循环
	public void iteratorList() {
		long start = System.currentTimeMillis();
		String value = "";
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			value = it.next();
		}
		long end = System.currentTimeMillis();
		long count = end - start;
		System.out.println("iterator 遍历时间" + count + "毫秒");
	}
}
