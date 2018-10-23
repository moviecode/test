package com.itaiti.share;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class MapTest {
	public Map<String, String> map = null;
	
	@Test
	public void test() {
		testForeach();
		tesetEntrySet();
		testKeySet();
	}
	
	@Before
	public void initMap() {
		if (null == map) {
			map = new HashMap<>();
		}

		map.put("a", "aa");
		map.put("b", "bb");
		map.put("c", "cc");
		map.put("d", "dd");
		map.put("e", "ee");
		map.put("f", "ff");
	}

	// 使用foreach遍历
	public void testForeach() {
		System.out.println("使用foreach遍历......");
		map.forEach((key, value) -> {
			System.out.println(key + "--" + value);
		});
	}

	// 使用EntrySet遍历
	public void tesetEntrySet() {
		System.out.println("使用EntrySet遍历......");
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "--" + entry.getValue());
		}
	}

	// 使用KeySet遍历,不建议使用这种方式，KeySet其实是遍历了两次，
	// 一次是专为Iterator对象，一次是从HashMap中根据key获取对应的值
	public void testKeySet() {
		System.out.println("使用KeySet遍历......");
		for (String key : map.keySet()) {
			System.out.println(key + "--" + map.get(key));
		}
		//Instant-->Date		LocalDateTime-->Calendar
	}
}
