package com.itaiti.thread;

		import java.util.ArrayList;
		import java.util.List;

/**
 * 阻塞时线程安全队列，基于数组实现
 * @version V1.0
 */
public class BlockQueueDemo {

	private List<String> list = new ArrayList<>();

	private int maxSize;
	// 自定义一把对象锁
	private Object lock = new Object();

	public BlockQueueDemo(int maxSize) {
		this.maxSize = maxSize;
		System.out.println("线程"+Thread.currentThread().getName()+",list maxSize:"+this.maxSize);
	}
	// wait,notify,notifyAll必须要放在synchronized块中，必须要活的当前对象的锁，才能进行wait/notify操作
	public void put(String element) {
		synchronized(lock) {
			//判断当前队列有没有满
			try {
				if(this.list.size() == this.maxSize) {
					System.out.println("线程"+Thread.currentThread().getName()+"已经满了，put方法进入等待状态......");
					lock.wait();
				}

			} catch(Exception e) {

			}
			this.list.add(element);
			System.out.println("线程"+Thread.currentThread().getName() + "向队列中添加了元素"+element);
			lock.notify();
//			lock.notifyAll();
		}
	}

	public void take() {
		synchronized(lock) {
			try {
				//为空，阻塞
				if(this.list.size() == 0) {
					System.out.println("线程"+Thread.currentThread().getName()+"已经空了，take方法进入等待状态......");
					lock.wait();
				}
			} catch(Exception e) {

			}
			String result = list.get(0);
			list.remove(0);
			System.out.println("线程"+Thread.currentThread().getName()+"取到了元素"+result);
			lock.notify();
//			lock.notifyAll();
		}

	}

	public static void main(String[] args) {
		BlockQueueDemo blockQueue = new BlockQueueDemo(5);
		new Thread(()->{
			for(int i=1; i<=18; i++) {
				blockQueue.put(""+i);
			}
		},"T1-put").start();

		new Thread(()->{
			for(int i=0; i<10; i++) {
				blockQueue.take();
			}
		},"T2-take").start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(blockQueue.list);

	}

	// ArrayBlockQueue
	// ReentrantLock,lock---->synchronized
	// Condition	await,sign--->wait,notify

}
