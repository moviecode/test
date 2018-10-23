package com.itaiti.concurrent;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.*;

/*
 *   ArrayBlockingQueue�ǡ��̰߳�ȫ���Ķ��У���LinkedList�Ƿ��̰߳�ȫ�ġ�
 *
 *   �����ǡ�����߳�ͬʱ�������ұ���queue����ʾ��
 *   (01) ��queue��ArrayBlockingQueue����ʱ���������������С�
 *   (02) ��queue��LinkedList����ʱ����������ConcurrentModificationException�쳣��
 *
 * @author skywang
 */
public class ArrayBlockingQueueDemo1 {

	// TODO: queue��LinkedList����ʱ����������
	// private static Queue<String> queue = new LinkedList<String>();
	private static Queue<String> queue = new ArrayBlockingQueue<String>(20);

	public static void main(String[] args) {

		// ͬʱ���������̶߳�queue���в�����
		new MyThread("ta").start();
		new MyThread("tb").start();
		//new MyThread("tc").start();
	}

	private static synchronized void printAll() {
		String value;
		Iterator<String> iter = queue.iterator();
		while (iter.hasNext()) {
			value = (String) iter.next();
			System.out.print(value + ", ");
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
				queue.add(val);
				// ͨ����Iterator������queue��
				printAll();
			}
		}
	}
}
