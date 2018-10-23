package com.itaiti.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

/**
 * http://www.importnew.com/26850.html
 * 1.如何让两个线程依次执行？
 * 2.那如何让 两个线程按照指定方式有序交叉运行呢？
 * 3.四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
 * 4.三个运动员各自准备，等到三个人都准备好后，再一起跑
 * 5.子线程完成某件任务后，把得到的结果回传给主线程
 * @date 2018年2月23日 下午2:26:59
 * @version V1.0
 */
public class MuliThreadDemo {
	
	public static void main(String[] args) {
		// demo1();
		// demo2();
		// demo3();
		// runDAfterABC();
		// runABCWhenAllReady();
		doTaskWithResultInWorker();
	}
	
	//********************demo1******************************
	// 有两个线程，一个是线程 A，另一个是线程 B，两个线程分别依次打印 1-3 三个数字即可
	private static void demo1() {
	    Thread A = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            printNumber("A");
	        }
	    });
	    Thread B = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            printNumber("B");
	        }
	    });
	    A.start();
	    B.start();
	}
	
	//********************demo2******************************
	// 我们希望 B 在 A 全部打印 完后再开始打印呢？我们可以利用 thread.join() 方法
	// Thread.join()，是用来指定当前主线程等待其他线程执行完毕后，再来继续执行Thread.join()后面的代码
	// Thread.join(long millis) 等待该线程终止的时间最长为 millis 毫秒。超时为 0 意味着要一直等下去。
	// Thread.join(long millis, int nanos) 等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒。
	private static void demo2() {
	    Thread A = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            printNumber("A");
	        }
	    });
	    Thread B = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            System.out.println("B 开始等待 A");
	            try {
	                A.join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            printNumber("B");
	        }
	    });
	    B.start();
	    A.start();
	}
	
	private static void printNumber(String threadName) {
	    int i=0;
	    while (i++ < 3) {
	        try {
	            Thread.sleep(100);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println(threadName + "print:" + i);
	    }
	}
	
	//********************demo3******************************
	// wait()方法、notify()方法和notifyAll()方法都需要先获取到对象上的锁后才能调用，否则会报java.lang.IllegalMonitorStateException
	// 当线程调用对象的wait()方法后，线程将进入等待状态并释放其持有的该对象上的锁（线程仍然持有其它对象的锁）
	// 当对象的notify()方法、notifyAll()方法被调用，或者线程被中断，或者线程等待了wait()方法指定的等待时间
	//（如果为wait(0)，则只有对象的notify()方法或notifyAll()方法被调用后线程才会退出等待状态）后，
	// 线程将退出等待状态，并和其它线程公平的竞争对象上的锁，一旦线程成功获取到对象锁，线程将从wait()方法调用返回，对象的同步状态和线程的同步状态将和wait()方法调时一样
	private static void demo3() {
	    Object lock = new Object();
	    Thread A = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            System.out.println("INFO: A 等待锁");
	            synchronized (lock) {
	                System.out.println("INFO: A 得到了锁 lock");
	                System.out.println("A 1");
	                try {
	                    System.out.println("INFO: A 准备进入等待状态，放弃锁 lock 的控制权");
	                    lock.wait();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                System.out.println("INFO: 有人唤醒了 A, A 重新获得锁 lock");
	                System.out.println("A 2");
	                System.out.println("A 3");
	            }
	        }
	    });
	    Thread B = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            System.out.println("INFO: B 等待锁");
	            synchronized (lock) {
	                System.out.println("INFO: B 得到了锁 lock");
	                System.out.println("B 1");
	                System.out.println("B 2");
	                System.out.println("B 3");
	                System.out.println("INFO: B 打印完毕，调用 notify 方法");
	                lock.notify();
	            }
	        }
	    });
	    A.start();
	    B.start();
	}
	
	//********************demo4******************************
	// 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
	// A B C 三个线程同时运行，各自独立运行完后通知 D；对 D 而言，只要 A B C 都运行完了，D 再开始运行。
	// 针对这种情况，我们可以利用 CountdownLatch 来实现这类通信方式。它的基本用法是：
	// 1.创建一个计数器，设置初始值，CountdownLatch countDownLatch = new CountDownLatch(2);
	// 2.在 等待线程 里调用 countDownLatch.await() 方法，进入等待状态，直到计数值变成 0；
	// 3.在其他线程 里，调用 countDownLatch.countDown() 方法，该方法会将计数值减小 1；
	// 4.当其他线程 的 countDown() 方法把计数值变成 0 时，等待线程 里的 countDownLatch.await() 立即退出，继续执行下面的代码
	/*CountDownLatch 就是一个倒计数器，我们把初始计数值设置为3，当 D 运行时，先调用 countDownLatch.await() 
	检查计数器值是否为 0，若不为 0 则保持等待状态；当A B C 各自运行完后都会利用countDownLatch.countDown()，
	将倒计数器减 1，当三个都运行完后，计数器被减至 0；此时立即触发 D 的 await() 运行结束，继续向下执行。
	因此，CountDownLatch 适用于一个线程去等待多个线程的情况。*/
	private static void runDAfterABC() {
		// final Semaphore semp = new Semaphore(5);
		int worker = 3;
		CountDownLatch countDownLatch = new CountDownLatch(worker);
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("D is waiting fro other three threads");
				try {
					// 等待，计数清零开始执行
					countDownLatch.await();
					System.out.println("All down, D starts working");
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		for(char threadName='A'; threadName <= 'C'; threadName++) {
			final String tn = String.valueOf(threadName);
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(tn +"is working");
					try {
						Thread.sleep(100);
					} catch(Exception e) {
						e.printStackTrace();
					}
					System.out.println(tn + "finished");
					// 计数-1
					countDownLatch.countDown();
				}
			}).start();
		}
	}
	
	//********************demo5******************************
	/**
	 * 三个运动员各自准备，等到三个人都准备好后，再一起跑
	 * CyclicBarrier 数据结构，它的基本用法是:
	 * 先创建一个公共 CyclicBarrier 对象，设置 同时等待 的线程数，CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
	 * 这些线程同时开始自己做准备，自身准备完毕后，需要等待别人准备完毕，这时调用 cyclicBarrier.await(); 即可开始等待别人
	 * 当指定的 同时等待 的线程数都调用了 cyclicBarrier.await();时，意味着这些线程都准备完毕好，然后这些线程才 同时继续执行。
	 */
	//实现代码如下，设想有三个跑步运动员，各自准备好后等待其他人，全部准备好后才开始跑：
	private static void runABCWhenAllReady() {
	    int runner = 3;
	    CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);
	    final Random random = new Random();
	    for (char runnerName='A'; runnerName <= 'C'; runnerName++) {
	        final String rN = String.valueOf(runnerName);
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                long prepareTime = random.nextInt(10000) + 100;
	                System.out.println(rN + "is preparing for time:" + prepareTime);
	                try {
	                    Thread.sleep(prepareTime);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                try {
	                    System.out.println(rN + "is prepared, waiting for others");
	                    // 当前运动员准备完毕，等待别人准备好
	                    cyclicBarrier.await(); 
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                } catch (BrokenBarrierException e) {
	                    e.printStackTrace();
	                }
	                // 所有运动员都准备好了，一起开始跑
	                System.out.println(rN + "starts running stamp:"+System.currentTimeMillis()); 
	            }
	        }).start();
	    }
	}
	
	//********************demo5******************************
	/**
	 * 子线程完成某件任务后，把得到的结果回传给主线程
	 * 实际的开发中，我们经常要创建子线程来做一些耗时任务，然后把任务执行结果回传给主线程使用这种情况在 Java 里要如何实现呢？
	 */
	// 回顾线程的创建，我们一般会把 Runnable 对象传给 Thread 去执行。Runnable定义如下：
	/*public interface Runnable {
	    public abstract void run();
	}*/
	// 可以看到 run() 在执行完后不会返回任何结果。那如果希望返回结果呢？这里可以利用另一个类似的接口类 Callable：
	/*@FunctionalInterface
	public interface Callable<V> {
	    *//**
	     * Computes a result, or throws an exception if unable to do so.
	     *
	     * @return computed result
	     * @throws Exception if unable to compute a result
	     *//*
	    V call() throws Exception;
	}*/
	// 可以看出 Callable 最大区别就是返回范型 V 结果。
	// 那么下一个问题就是，如何把子线程的结果回传回来呢？在 Java 里，有一个类是配合 Callable 使用的：FutureTask，不过注意，它获取结果的 get 方法会阻塞主线程。
	// 举例，我们想让子线程去计算从 1 加到 100，并把算出的结果返回到主线程。
	private static void doTaskWithResultInWorker() {
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("Task starts");
				Thread.sleep(1000);
				int result = 0;
				for(int i=0; i<=100; i++) {
					result += i;
				}
				System.out.println("Task finished and return result");
				return result;
			}
		};
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		new Thread(futureTask).start();
		try {
			System.out.println("Before futureTask.get()");
			System.out.println("Result:"+futureTask.get());
			System.out.println("After futureTask.get()");
		} catch(InterruptedException e) {
			e.printStackTrace();
		} catch(ExecutionException e) {
			e.printStackTrace();
		}
	}
}
