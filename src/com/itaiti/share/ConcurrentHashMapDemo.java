package com.itaiti.share;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

/*
 *   ConcurrentHashMap�ǡ��̰߳�ȫ���Ĺ�ϣ����HashMap�Ƿ��̰߳�ȫ�ġ�
 *
 *   �����ǡ�����߳�ͬʱ�������ұ���map����ʾ��
 *   (01) ��map��ConcurrentHashMap����ʱ���������������С�
 *   (02) ��map��HashMap����ʱ����������ConcurrentModificationException�쳣��
 *
 * @author skywang
 */
public class ConcurrentHashMapDemo {

	// TODO: map��HashMap����ʱ����������
	//private static Map<String, String> map = new HashMap<String, String>();
	private static Map<String, String> map = new ConcurrentHashMap<String, String>();
	
	public static void main(String[] args) {
		// ͬʱ���������̶߳�map���в�����
		new MyThread("ta").start();
		new MyThread("tb").start();
	}

	private static void printAll() {
		String key, value;
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			key = (String) entry.getKey();
			value = (String) entry.getValue();
			System.out.print(key + " - " + value + ", ");
		}
		System.out.println();
	}

	private static class MyThread extends Thread {
		MyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			int i = 0;
			while (i++ < 6) {
				// ���߳����� + "-" + "���"
				String val = Thread.currentThread().getName() + i;
				map.put(String.valueOf(i), val);
				// ͨ����Iterator������map��
				printAll();
			}
		}
	}
}
