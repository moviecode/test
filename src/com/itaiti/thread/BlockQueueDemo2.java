package com.itaiti.thread;

import java.util.ArrayList;
import java.util.List;

public class BlockQueueDemo2 {

	private final List<String> list = new ArrayList<>();
	private final Object lock = new Object();
	private int maxCount;

	public BlockQueueDemo2(int maxCount) {
		this.maxCount = maxCount;
		System.out.println("当前主线程：" + Thread.currentThread().getName() + ",maxCount:" + this.maxCount);
	}

	public void put(String element) {
		synchronized(this.lock) {
			if(this.list.size() == maxCount) {
				System.out.println("线程" + Thread.currentThread().getName() + "已经满了，进入等待状态.....");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("线程" + Thread.currentThread().getName() + "添加一个元素" + element);
			this.list.add(element);
//			this.notifyAll();
		}
	}

	public void take() {
		synchronized(this.lock) {
			if(this.list.size() == 0) {
				System.out.println("线程" + Thread.currentThread().getName() + "已经空了，进入等待状态.....");
				try {
					this.lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String result = this.list.get(0);
			System.out.println("线程" + Thread.currentThread().getName() + "删除一个元素" + result);
			this.list.remove(0);
//			this.notifyAll();
		}
	}

	public static void main(String[] args) {
		BlockQueueDemo2 blockQueue = new BlockQueueDemo2(5);
		new Thread(() -> {
			blockQueue.put("1");
			blockQueue.put("2");
			blockQueue.put("3");
			blockQueue.put("4");
			blockQueue.put("5");
			blockQueue.put("6");
			blockQueue.put("7");
			blockQueue.put("8");
			blockQueue.put("9");
		}, "T1-put").start();


		new Thread(() -> {
			blockQueue.take();
			blockQueue.take();
			blockQueue.take();
		}, "T2-take").start();

		try {
			Thread.sleep(3 * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(blockQueue.list);
	}

}
