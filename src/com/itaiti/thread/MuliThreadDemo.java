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
 * 1.����������߳�����ִ�У�
 * 2.������� �����̰߳���ָ����ʽ���򽻲������أ�
 * 3.�ĸ��߳� A B C D������ D Ҫ�ȵ� A B C ȫִ����Ϻ��ִ�У����� A B C ��ͬ�����е�
 * 4.�����˶�Ա����׼�����ȵ������˶�׼���ú���һ����
 * 5.���߳����ĳ������󣬰ѵõ��Ľ���ش������߳�
 * @date 2018��2��23�� ����2:26:59
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
	// �������̣߳�һ�����߳� A����һ�����߳� B�������̷ֱ߳����δ�ӡ 1-3 �������ּ���
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
	// ����ϣ�� B �� A ȫ����ӡ ����ٿ�ʼ��ӡ�أ����ǿ������� thread.join() ����
	// Thread.join()��������ָ����ǰ���̵߳ȴ������߳�ִ����Ϻ���������ִ��Thread.join()����Ĵ���
	// Thread.join(long millis) �ȴ����߳���ֹ��ʱ���Ϊ millis ���롣��ʱΪ 0 ��ζ��Ҫһֱ����ȥ��
	// Thread.join(long millis, int nanos) �ȴ����߳���ֹ��ʱ���Ϊ millis ���� + nanos ���롣
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
	            System.out.println("B ��ʼ�ȴ� A");
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
	// wait()������notify()������notifyAll()��������Ҫ�Ȼ�ȡ�������ϵ�������ܵ��ã�����ᱨjava.lang.IllegalMonitorStateException
	// ���̵߳��ö����wait()�������߳̽�����ȴ�״̬���ͷ�����еĸö����ϵ������߳���Ȼ�����������������
	// �������notify()������notifyAll()���������ã������̱߳��жϣ������̵߳ȴ���wait()����ָ���ĵȴ�ʱ��
	//�����Ϊwait(0)����ֻ�ж����notify()������notifyAll()���������ú��̲߳Ż��˳��ȴ�״̬����
	// �߳̽��˳��ȴ�״̬�����������̹߳�ƽ�ľ��������ϵ�����һ���̳߳ɹ���ȡ�����������߳̽���wait()�������÷��أ������ͬ��״̬���̵߳�ͬ��״̬����wait()������ʱһ��
	private static void demo3() {
	    Object lock = new Object();
	    Thread A = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            System.out.println("INFO: A �ȴ���");
	            synchronized (lock) {
	                System.out.println("INFO: A �õ����� lock");
	                System.out.println("A 1");
	                try {
	                    System.out.println("INFO: A ׼������ȴ�״̬�������� lock �Ŀ���Ȩ");
	                    lock.wait();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                System.out.println("INFO: ���˻����� A, A ���»���� lock");
	                System.out.println("A 2");
	                System.out.println("A 3");
	            }
	        }
	    });
	    Thread B = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            System.out.println("INFO: B �ȴ���");
	            synchronized (lock) {
	                System.out.println("INFO: B �õ����� lock");
	                System.out.println("B 1");
	                System.out.println("B 2");
	                System.out.println("B 3");
	                System.out.println("INFO: B ��ӡ��ϣ����� notify ����");
	                lock.notify();
	            }
	        }
	    });
	    A.start();
	    B.start();
	}
	
	//********************demo4******************************
	// �ĸ��߳� A B C D������ D Ҫ�ȵ� A B C ȫִ����Ϻ��ִ�У����� A B C ��ͬ�����е�
	// A B C �����߳�ͬʱ���У����Զ����������֪ͨ D���� D ���ԣ�ֻҪ A B C ���������ˣ�D �ٿ�ʼ���С�
	// ���������������ǿ������� CountdownLatch ��ʵ������ͨ�ŷ�ʽ�����Ļ����÷��ǣ�
	// 1.����һ�������������ó�ʼֵ��CountdownLatch countDownLatch = new CountDownLatch(2);
	// 2.�� �ȴ��߳� ����� countDownLatch.await() ����������ȴ�״̬��ֱ������ֵ��� 0��
	// 3.�������߳� ����� countDownLatch.countDown() �������÷����Ὣ����ֵ��С 1��
	// 4.�������߳� �� countDown() �����Ѽ���ֵ��� 0 ʱ���ȴ��߳� ��� countDownLatch.await() �����˳�������ִ������Ĵ���
	/*CountDownLatch ����һ���������������ǰѳ�ʼ����ֵ����Ϊ3���� D ����ʱ���ȵ��� countDownLatch.await() 
	��������ֵ�Ƿ�Ϊ 0������Ϊ 0 �򱣳ֵȴ�״̬����A B C ����������󶼻�����countDownLatch.countDown()��
	������������ 1����������������󣬼����������� 0����ʱ�������� D �� await() ���н�������������ִ�С�
	��ˣ�CountDownLatch ������һ���߳�ȥ�ȴ�����̵߳������*/
	private static void runDAfterABC() {
		// final Semaphore semp = new Semaphore(5);
		int worker = 3;
		CountDownLatch countDownLatch = new CountDownLatch(worker);
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("D is waiting fro other three threads");
				try {
					// �ȴ����������㿪ʼִ��
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
					// ����-1
					countDownLatch.countDown();
				}
			}).start();
		}
	}
	
	//********************demo5******************************
	/**
	 * �����˶�Ա����׼�����ȵ������˶�׼���ú���һ����
	 * CyclicBarrier ���ݽṹ�����Ļ����÷���:
	 * �ȴ���һ������ CyclicBarrier �������� ͬʱ�ȴ� ���߳�����CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
	 * ��Щ�߳�ͬʱ��ʼ�Լ���׼��������׼����Ϻ���Ҫ�ȴ�����׼����ϣ���ʱ���� cyclicBarrier.await(); ���ɿ�ʼ�ȴ�����
	 * ��ָ���� ͬʱ�ȴ� ���߳����������� cyclicBarrier.await();ʱ����ζ����Щ�̶߳�׼����Ϻã�Ȼ����Щ�̲߳� ͬʱ����ִ�С�
	 */
	//ʵ�ִ������£������������ܲ��˶�Ա������׼���ú�ȴ������ˣ�ȫ��׼���ú�ſ�ʼ�ܣ�
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
	                    // ��ǰ�˶�Ա׼����ϣ��ȴ�����׼����
	                    cyclicBarrier.await(); 
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                } catch (BrokenBarrierException e) {
	                    e.printStackTrace();
	                }
	                // �����˶�Ա��׼�����ˣ�һ��ʼ��
	                System.out.println(rN + "starts running stamp:"+System.currentTimeMillis()); 
	            }
	        }).start();
	    }
	}
	
	//********************demo5******************************
	/**
	 * ���߳����ĳ������󣬰ѵõ��Ľ���ش������߳�
	 * ʵ�ʵĿ����У����Ǿ���Ҫ�������߳�����һЩ��ʱ����Ȼ�������ִ�н���ش������߳�ʹ����������� Java ��Ҫ���ʵ���أ�
	 */
	// �ع��̵߳Ĵ���������һ���� Runnable ���󴫸� Thread ȥִ�С�Runnable�������£�
	/*public interface Runnable {
	    public abstract void run();
	}*/
	// ���Կ��� run() ��ִ����󲻻᷵���κν���������ϣ�����ؽ���أ��������������һ�����ƵĽӿ��� Callable��
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
	// ���Կ��� Callable ���������Ƿ��ط��� V �����
	// ��ô��һ��������ǣ���ΰ����̵߳Ľ���ش������أ��� Java ���һ��������� Callable ʹ�õģ�FutureTask������ע�⣬����ȡ����� get �������������̡߳�
	// �����������������߳�ȥ����� 1 �ӵ� 100����������Ľ�����ص����̡߳�
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
