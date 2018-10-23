package com.itaiti.thread;

import lombok.Synchronized;

public class OjbectTest {
	OjbectTest objectTest = new OjbectTest();
	
	public static void main(String[] args) {
		
	}
	
	public void waitTest() {
		new Thread() {
			public void run() {
				synchronized(objectTest) {
					try {
						objectTest.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
