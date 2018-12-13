package com.itaiti.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
/**
 * cause by https://cloud.tencent.com/developer/article/1330497
 * 使用线程池时候当程序结束时候记得调用shutdown关闭线程池
 * @version V1.0
 */
public class TestShutDown {

	static void asynExecuteOne() {
		ExecutorService executor = Executors.newFixedThreadPool(4);
//				Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("--asynExecuteOne--");
			}
		});
		// shutdown调用后，不可以再submit新的task，已经submit的将继续执行。
		// shutdownNow试图停止当前正执行的task，并返回尚未执行的task的list
//		executor.shutdown();
//		executor.shutdownNow();
	}

	static void asynExecuteTwo() {
		ExecutorService executor = Executors.newFixedThreadPool(4);
//				Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("--asynExecuteTwo--");
			}
		});
//		executor.shutdown();
//		executor.shutdownNow();
	}

	//当不调用线程池对象的shutdown方法后，当线程池里面的任务执行完毕后主线程这个JVM不会退出。
	//只有调用了线程池的shutdown方法后当线程池任务执行完毕后线程池资源才会释放。
	/*JVM退出的条件是当前不存在用户线程，而线程池默认的ThreadFactory创建的线程是用户线程，
	线程池默认的线程工厂创建创建的都是用户线程。而线程池里面的核心线程是一直会存在的，如果没有任务则会阻塞，
	所以线程池里面的用户线程一直会存在.而shutdown方法的作用就是让这些核心线程终止*/
	public static void main(String[] args) {
		// 主线程开始执行
		System.out.println("sync execute start");
		// 子线程1开始执行
		asynExecuteOne();
		// 子线程2开始执行
		asynExecuteTwo();
		// 主线程执行完毕
		System.out.println("--execute over--");

	}
}
