package com.itaiti.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList???????????????????
 * 
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017??1??22?? ????11:44:07
 * @version V1.0
 */
public class ListTest {
	private static List<String> list = new ArrayList<>();

	public static void main(String[] args) {
		ListTest mapTest = new ListTest();
		mapTest.initList(list);
		mapTest.foreach(list);
		mapTest.forlist(list);
		mapTest.iteratorList(list);
	}

	// list ?????????100????????
	public List<String> initList(List<String> list) {
		int i = 0;
		int num = 10000000;
		long start = System.currentTimeMillis();
		for (i = 0; i < num; i++) {
			list.add("list" + i);
		}
		long end = System.currentTimeMillis();
		long count = end - start;
		System.out.println("initList ???" + count + "????");
		return list;
	}

	// list ??????? foreach
	public void foreach(List<String> list) {
		long start = System.currentTimeMillis();
		String value = "";
		for (String data : list) {
			value = data;
		}

		long end = System.currentTimeMillis();
		long count = end - start;
		System.out.println("foreach ??????" + count + "????");
	}

	// list??????? for
	public void forlist(List<String> list) {
		long start = System.currentTimeMillis();
		int i = 0;
		String value = "";
		for (i = 0; i < list.size(); i++) {
			value = list.get(i);
		}
		long end = System.currentTimeMillis();
		long count = end - start;
		System.out.println("for list.size() ???????" + count + "????");
	}

	// Iterator ???????
	public void iteratorList(List<String> list) {
		long start = System.currentTimeMillis();
		String value = "";
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			value = it.next();
		}
		long end = System.currentTimeMillis();
		long count = end - start;
		System.out.println("iterator ???????" + count + "????");
	}
}
