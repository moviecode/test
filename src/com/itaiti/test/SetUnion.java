package com.itaiti.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SetUnion {
	
	
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		List<Integer> list2 = new ArrayList<>();
		list2.add(2);
		list2.add(3);
		list2.add(5);
		list2.add(7);
		
		//List<Integer> unionList = test2(list, list2);
		//unionList.forEach(v -> System.out.println(v));
		//test4(list, list2);
		int[] a = {1,3,5,9,10,15};
		int[] b = {1,5,7,10,20};
		int[] intersection = intersect(a, b);
		for(int i : intersection){
			System.out.println(i);
		}
	}
	
	/**
	 * 求交集 m*n
	 * @param list
	 * @param list2
	 * @return
	 */
	public static List<Integer> test(List<Integer> list, List<Integer> list2){
		List<Integer> unionList = new ArrayList<>();
		for(Integer v : list){
			for(Integer v2 : list2){
				if(v == v2)
					unionList.add(v);
			}
		}
		return unionList;
	}
	
	/**
	 * 求交集 m+n
	 * @param list
	 * @param list2
	 * @return
	 */
	public static List<Integer> test2(List<Integer> list, List<Integer> list2){
		List<Integer> unionList = new ArrayList<>();
		Map<Integer,Integer> map = new HashMap<>();
		for(Integer v : list){
			map.put(v, v);
		}
		for(Integer v2 : list2){
			if(map.get(v2) != null){
				unionList.add(v2);
			}
		}
		return unionList;
	}
	
	/**
	 * 求交集 
	 * @param list
	 * @param list2
	 */
	public static void test3(List<Integer> list, List<Integer> list2){
		list.retainAll(list2);
		list.forEach(v -> System.out.println(v));
	}
	
	/**
	 * 求并集
	 * @param list
	 * @param list2
	 */
	public static void test4(List<Integer> list, List<Integer> list2){
		list.addAll(list2);
		list.forEach(v -> System.out.println(v));
	}
	
	/**
	 * 求交集，方式一
	 */
	private static Set<Integer> setMethod(int[] a,int[] b){
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for(int i=0; i<a.length; i++) {
            set.add(a[i]);
        }
        for(int j=0; j<b.length; j++) {
            if(!set.add(b[j]))
                set2.add(b[j]);
        }
        return set2;
    }
	
	/**
	 * 求交集，方式二
	 */
	private static Set<Integer> forMethod(int[] a,int[] b){
        Set<Integer> set=new HashSet<Integer>();
        int i=0,j=0;
        while(i<a.length && j<b.length){
            if(a[i]<b[j])
                i++;
            else if(a[i]>b[j])
                j++;
            else{
                set.add(a[i]);
                i++;
                j++;
            }
        }
        return set;
    }
	
	/**
	 * 求交集，方式三
	 */
	private static int[] intersect(int[] a, int[] b) {
        if (a[0] > b[b.length - 1] || b[0] > a[a.length - 1]) {
            return new int[0];
        }
        int[] intersection = new int[Math.max(a.length, b.length)];
        int offset = 0;
        for (int i = 0, s = i; i < a.length && s < b.length; i++) {
            while (a[i] > b[s]) {
                s++;
            }
            if (a[i] == b[s]) {
                intersection[offset++] = b[s++];
            }
            while (i < (a.length - 1) && a[i] == a[i + 1]) {
                i++;
            }
        }
        if (intersection.length == offset) {
            return intersection;
        }
        int[] duplicate = new int[offset];
        System.arraycopy(intersection, 0, duplicate, 0, offset);
        return duplicate;
    }
}
