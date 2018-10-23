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

	// ʹ��foreach����
	public void testForeach() {
		System.out.println("ʹ��foreach����......");
		map.forEach((key, value) -> {
			System.out.println(key + "--" + value);
		});
	}

	// ʹ��EntrySet����
	public void tesetEntrySet() {
		System.out.println("ʹ��EntrySet����......");
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "--" + entry.getValue());
		}
	}

	// ʹ��KeySet����,������ʹ�����ַ�ʽ��KeySet��ʵ�Ǳ��������Σ�
	// һ����רΪIterator����һ���Ǵ�HashMap�и���key��ȡ��Ӧ��ֵ
	public void testKeySet() {
		System.out.println("ʹ��KeySet����......");
		for (String key : map.keySet()) {
			System.out.println(key + "--" + map.get(key));
		}
		//Instant-->Date		LocalDateTime-->Calendar
	}
}
