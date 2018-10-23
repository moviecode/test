package com.itaiti.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ArrayBlockingQueueTest {
	
	public static final ArrayBlockingQueueTest ABQ = new ArrayBlockingQueueTest();
	
	/** ���ƴ���ÿ�������� */
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
     * ����ÿ���ȡһ��
     * @return
     */
    public synchronized ArrayBlockingQueueTest oncePerSecond() {
    	for (;;) {
            try {
                Long now = System.currentTimeMillis();
                // ��ָ����Ԫ�ز���˶��е�β��������ö������������ڵ���ָ���ĵȴ�ʱ��֮ǰ�ȴ����õĿռ䡣
                // �ȴ�100���룬�������ʧ�ܣ�ִ��if�߼��� now-firstTime����1s���Ƴ����е�ͷ
                if (!queue.offer(now, 100, TimeUnit.MILLISECONDS)) {
                	// ��ȡ�����Ƴ��˶��е�ͷ������˶���Ϊ�գ��򷵻� null��
                    Long firstTime = queue.peek();
                    System.out.println("firstTime:"+firstTime);
                    if (firstTime != null && now - firstTime > 1000) {
                    	// ��ȡ���Ƴ��˶��е�ͷ������˶���Ϊ�գ��򷵻� null��
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
