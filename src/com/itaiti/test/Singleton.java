package com.itaiti.test;

/**
 * 懒汉模式
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017年1月17日 下午6:24:08
 * @version V1.0
 */
public class Singleton {
	private static Singleton instance = null;
	private Singleton() {
		
	}
	//方式一
	public static Singleton getInstance(){
		if(null == instance){
			instance = new Singleton();
		}
		return instance;
	}
	
	//方式二：方法同步
	public static synchronized Singleton getInstance2(){
		if(null == instance){
			instance = new Singleton();
		}
		return instance;
	}
	
	//方式三：双重检查
	public static Singleton getInstance3(){
		if(null == instance){
			synchronized(Singleton.class){
				if(null == instance){
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	//方式四：静态内类
	private static class LazyHolder{
		private static final Singleton INSTANCE = new Singleton();
	}
	public static final Singleton getInstance4(){
		return LazyHolder.INSTANCE;
	}
}

/**
 * 饿汉模式
 * @author itaiti
 * @email fei.zhang@mtime.com
 * @date 2017年1月17日 下午6:30:59
 * @version V1.0
 */
class Singleton2{
	private static Singleton2 instance = new Singleton2();
	private Singleton2(){}
	public static Singleton2 getInstance(){
		return instance;
	}
}
