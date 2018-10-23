package com.itaiti.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * java�ź��� 
 * @author itaiti
 * @email fei.zhang@mtime.com
 * @date 2017��8��31�� ����6:48:26
 * @version V1.0
 */
public class TestSemaphore {
	/*
	 Semaphore��ǰ�ڶ��̻߳����±�����ʹ�ã�����ϵͳ���ź����Ǹ�����Ҫ�ĸ���ڽ��̿��Ʒ��涼��Ӧ�á�Java ������ ��Semaphore ���Ժ���������ź������ƣ�
	 Semaphore���Կ���ĳ����Դ�ɱ�ͬʱ���ʵĸ�����ͨ�� acquire() ��ȡһ����ɣ����û�о͵ȴ����� release() �ͷ�һ����ɡ�������Windows�¿������ù����ļ������ͻ��˷��ʸ����� 
	 Semaphoreʵ�ֵĹ��ܾ����Ʋ�����5���ӣ�������10����Ҫ�ϲ�������ôͬʱֻ���ж��ٸ���ȥ�ϲ����أ�ͬʱֻ����5�����ܹ�ռ�ã���5������ ���κ�һ�����ÿ���
	   ���еȴ�������5����������һ���˿���ռ���ˡ�����ȴ���5�����п��������������Ȼ��ᣬҲ�����ǰ��������󵽵�˳���û��ᣬ��ȡ���ڹ���Semaphore����ʱ����Ĳ���ѡ�
	   �����ź�����Semaphore�������ʵ�ֻ������Ĺ��ܣ����ҿ�������һ���̻߳���ˡ�������������һ���߳��ͷš����������Ӧ���������ָ���һЩ���ϡ�
	 Semaphoreά���˵�ǰ���ʵĸ������ṩͬ�����ƣ�����ͬʱ���ʵĸ����������ݽṹ��������Ա��桰���ޡ��Ľڵ㣬��Semaphore����ʵ�����޴�С������
	   ���������� ReentrantLock Ҳ����ʵ�ָù��ܣ���ʵ����Ҫ����Щ�� 
	   �����Demo��������һ��ֻ��5����ɵ�Semaphore������20���߳�Ҫ���������Դ��ͨ��acquire()��release()��ȡ���ͷŷ�����ɡ�
	 */
	public static void main(String[] args) {
		// �̳߳�
		ExecutorService exec = Executors.newCachedThreadPool();
		//Executors.newFixedThreadPool();
		// ֻ��5���߳�ͬʱ����
		final Semaphore semp = new Semaphore(5);
		// ģ��20���ͻ��˷���
		for (int index = 0; index < 20; index++) {
			final int NO = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						
						// ��ȡ���
						semp.acquire();
						System.out.println("Accessing: " + NO);
						Thread.sleep((long) (Math.random() * 10000));
						// ��������ͷ�
						semp.release();
						System.out.println("-----------------" + semp.availablePermits());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);
		}

		// �˳��̳߳�
		exec.shutdown();
	}
}
