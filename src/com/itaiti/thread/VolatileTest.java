package com.itaiti.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {

	public static int count = 0;	//	result:<=100,线程不安全

	/**
	 * volatile	保证内存可见性，防止指令重排序，一写多读是线程安全的，多写多读不能保证线程安全
	 */
//	public static volatile int count = 0;	//	result:<=100,线程不安全
	// CAS compare and swap,乐观锁，可以保证运行结果正确
//	public static AtomicInteger count = new AtomicInteger();

	public synchronized static void increase() {	//方法枷锁，保证多线程操作的原子性，可以实现线程安全，但性能底下
//	public static void increase() {
		/*try {
			Thread.sleep(3L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		count++;
//		count.incrementAndGet();
	}

	public static void main(String[] args) {

		for(int i=0; i<100; i++) {
//			increase();
			new Thread(VolatileTest:: increase).start();
			/*new Thread(()->{
				increase();
			}).start();*/
		}

		// sleep几秒钟，保证子线程执行完毕后，再从主线程输入count值
		try {
			Thread.sleep(5 * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(count);
	}

	/*public final synchronized void join(long millis) throws InterruptedException {
		long base = System.currentTimeMillis();
		long now = 0;
		if (millis < 0) {
			throw new IllegalArgumentException("timeout value is negative");
		}
		if (millis == 0) {
			while (isAlive()) {
				wait(0);
			}
		} else {
			while (isAlive()) {
				long delay = millis - now;
				if (delay <= 0) {
					break;
				}
				wait(delay);
				now = System.currentTimeMillis() - base;
			}
		}
	}*/

}
