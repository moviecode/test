package com.itaiti.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CountTest {
	
	public static int count = 0;
	
	// 原子类操作	CAS(Compare And Swap)比较并替换
	public static AtomicInteger atomicCount = new AtomicInteger(0);
	
	public static void main(String[] args) {
		for(int i=0; i<2; i++) {
			new Thread(new Runnable() {
				public void run() {
					try{
						Thread.sleep(10);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
					for(int j=0; j<100; j++) {
						synchronized(CountTest.class) {
							count ++;
						}
						atomicCount.incrementAndGet();
//						count ++;
					}
				}
			}).start();
		}
		
		try{
			Thread.sleep(2000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("count=" + count);
		System.out.println("atomicCount="+ atomicCount.get());
	}

}
