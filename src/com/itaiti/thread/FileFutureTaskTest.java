package com.itaiti.thread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;

public class FileFutureTaskTest {
	
	private Logger logger = LoggerManager.getLogger(FileFutureTaskTest.class);
	
	@Test
	public void test() {
		logger.info("........start");
		FileConfig fileConfig = new FileConfig();
		fileConfig.setCouponId("1");
		fileConfig.setType("10");
		File file = new File("D:/test/userId.txt");
		FileReadTask fileReadTask = new FileReadTask(file, fileConfig);
		FileFutureTask fft = new FileFutureTask(fileReadTask);
		
		Thread t = new Thread(fft);
		t.start();
		//ExecutorService executor = Executors.newFixedThreadPool(5);
		//executor.submit(fft);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("........end");
	}
}
