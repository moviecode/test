package com.itaiti.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

public class Log4jTest {
	
	Logger logger = Logger.getLogger(Log4jTest.class);
	
	/**
	 * ��־����ALL<DEBUG<INFO<WARN<ERROR<FATAL<OFF�������ִ�Сд
	 */
	@Test
	public void log4jTest() {
		PropertyConfigurator.configure(ClassLoader.getSystemResource("log4j.properties"));
		Log4jTest log4jTest = new Log4jTest();
		//log4jTest.warnTest("itaiti warn test..."+System.currentTimeMillis());
		log4jTest.infoTest("itaiti info test..."+System.currentTimeMillis());
		log4jTest.errorTest("itaiti error test..."+System.currentTimeMillis());
		log4jTest.debugTest("itaiti debug test..."+System.currentTimeMillis());
	}
	
	public void warnTest(String content) {
		logger.warn(content);
	}
	
	public void infoTest(String content) {
		logger.info(content);
	}
	
	public void errorTest(String content) {
		logger.error(content);
	}
	
	public void debugTest(String content) {
		logger.debug(content);
	}
	
	@Test
	public void classAndClassLoaderTest() {
		System.out.println(Log4jTest.class.getResource(""));
        System.out.println(Log4jTest.class.getResource("/"));
		// path���ԡ�/'��ͷʱ��Ĭ���ǴӴ������ڵİ���ȡ��Դ
		//System.out.println(Log4jTest.class.getResource("log4j.properties").getPath());
		// path  �ԡ�/'��ͷʱ�����Ǵ�ClassPath���»�ȡ
		System.out.println(Log4jTest.class.getResource("/log4j.properties").getPath());
		
		System.out.println(ClassLoader.getSystemClassLoader().getResource("log4j.properties"));
		System.out.println(ClassLoader.getSystemResource("log4j.properties").getPath());
	}
}
