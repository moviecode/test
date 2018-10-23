package com.itaiti.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ArrayBlockingQueueTest {
	
	public static final ArrayBlockingQueueTest ABQ = new ArrayBlockingQueueTest();
	
	/** 控制代理每秒请求数 */
    private ArrayBlockingQueue<Long> queue = new ArrayBlockingQueue<>(4);
    
    @Test
    public void test() {
    	while(true) {
    		oncePerSecond();
    	}
    }
    @Test
    public void apiTest() {
    	queue.add(1L);
    	queue.add(2L);
    	queue.add(3L);
    	System.out.println(queue);
    	System.out.println("offer:"+queue.offer(4L));
    	System.out.println("offer after:"+queue);
    	System.out.println("peek:"+queue.peek());
    	System.out.println("peek after:"+queue);
    	System.out.println("poll:"+queue.poll());
    	System.out.println("poll after:"+queue);
    	System.out.println("remove:"+queue.remove(2L));
    	System.out.println("remove after:"+queue);
    }
    
    /**
     * 控制每秒获取一次
     * @return
     */
    public synchronized ArrayBlockingQueueTest oncePerSecond() {
    	for (;;) {
            try {
                Long now = System.currentTimeMillis();
                // 将指定的元素插入此队列的尾部，如果该队列已满，则在到达指定的等待时间之前等待可用的空间。
                // 等待100毫秒，插入队列失败，执行if逻辑， now-firstTime大于1s，移除队列的头
                if (!queue.offer(now, 100, TimeUnit.MILLISECONDS)) {
                	// 获取但不移除此队列的头；如果此队列为空，则返回 null。
                    Long firstTime = queue.peek();
                    System.out.println("firstTime:"+firstTime);
                    if (firstTime != null && now - firstTime > 1000) {
                    	// 获取并移除此队列的头，如果此队列为空，则返回 null。
                        System.out.println("poll:"+queue.poll());;
                    }
                } else {
                    break;
                }
            } catch (InterruptedException e) {
            }
        }
    	System.out.println(System.currentTimeMillis());
    	return ABQ;
    }
}
