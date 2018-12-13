package com.itaiti.thread;

public class WaitNotifyDemo {
	Object obj = new Object();

	public synchronized void run1() {
		System.out.println("进入run1方法");
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("run1方法执行完毕");
	}

	public synchronized void run2() {
		System.out.println("进入run2方法");
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("run2执行完毕");
	}

	public synchronized void run3() {
		System.out.println("进入run3方法");
//		this.notify();//随机唤醒一个等待队列中的线程
//		this.notifyAll();//唤醒所有等待队列中的线程，去竞争锁，拿到所得线程进入就绪状态，等待cpu调度
		System.out.println("run3执行完成");
	}

	// wait/notify从属于某个特定的所对象
	// 任意一个对象都可以当做锁对象来使用		synchronized(lock)-->
	public static void main(String[] args) {
		WaitNotifyDemo demo = new WaitNotifyDemo();
		new Thread(()->{
			demo.run1();
		}).start();

		new Thread(()->{
			demo.run2();
		}).start();

		new Thread(()->{
			demo.run3();
		}).start();
	}

}
