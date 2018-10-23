package com.itaiti.io.fnio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {
	
	private ExecutorService executor;
	
	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
		executor = new ThreadPoolExecutor(
				Runtime.getRuntime().availableProcessors(),// corePoolSize,
				maxPoolSize,// maximumPoolSize
				120L,//keepAliveTime 
				TimeUnit.SECONDS,// unit 
				new ArrayBlockingQueue<Runnable>(queueSize)//workQueue
				);
		
	}
	
	public void execute(Runnable task) {
		executor.execute(task);
	}
}
