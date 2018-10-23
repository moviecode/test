package com.itaiti.failfast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FailFastTest {
	
	private static List<Integer> list = new ArrayList<>();
	
	/**
	 * 线程one迭代list 
	 * @author itaiti
	 * @email itaiti@163.com
	 * @date 2017年6月7日 下午2:51:39
	 * @version V1.0
	 */
	private static class ThreadOne extends Thread {
		public void run() {
			Iterator<Integer> iterator = list.iterator();
			while(iterator.hasNext()) {
				int i = iterator.next();
				System.out.println("ThreadOne 遍历：" + i);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 当i==3时，修改list
	 * @author itaiti
	 * @email fei.zhang@mtime.com
	 * @date 2017年6月7日 下午2:51:46
	 * @version V1.0
	 */
	private static class ThreadTwo extends Thread {
		public void run() {
			int i = 0;
			while(i < 6) {
				System.out.println("ThreadTwo run："+i);
				if(i == 3) {
					list.remove(i);
					//list.add(i);
				}
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			list.add(i);
		}
		new ThreadOne().start();
		new ThreadTwo().start();
	}
	
	/*运行结果：
	ThreadOne 遍历：0
	ThreadTwo run：0
	ThreadTwo run：1
	ThreadTwo run：2
	ThreadTwo run：3
	ThreadTwo run：4
	ThreadTwo run：5
	Exception in thread "Thread-0" java.util.ConcurrentModificationException  
		    at java.util.ArrayList$Itr.checkForComodification(Unknown Source)  
		    at java.util.ArrayList$Itr.next(Unknown Source)  
		    at test.ArrayListTest$threadOne.run(ArrayListTest.java:23)*/
}
