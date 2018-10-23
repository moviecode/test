package com.itaiti.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.junit.Test;

public class ConcurrentHashMapTest {
	
	@Test
	public void test() {
		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		KeySetView<String, String> keySetView = map.keySet();
		Iterator<String> it = keySetView.iterator();
		while(it.hasNext()){
			System.out.println(map.get(it.next()));
		}
		
		Map<String,String> hashMap = new HashMap<>();
		//established
	}
	
	@Test
	public void spreadTest(){
		int hashCode = "test123".hashCode();
		System.out.println(hashCode);
		System.out.println(spread(hashCode));
	}
	static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
	static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }
}
