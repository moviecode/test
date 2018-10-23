package com.itaiti.test;

/**
 * ����ģʽ
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017��1��17�� ����6:24:08
 * @version V1.0
 */
public class Singleton {
	private static Singleton instance = null;
	private Singleton() {
		
	}
	//��ʽһ
	public static Singleton getInstance(){
		if(null == instance){
			instance = new Singleton();
		}
		return instance;
	}
	
	//��ʽ��������ͬ��
	public static synchronized Singleton getInstance2(){
		if(null == instance){
			instance = new Singleton();
		}
		return instance;
	}
	
	//��ʽ����˫�ؼ��
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
	
	//��ʽ�ģ���̬����
	private static class LazyHolder{
		private static final Singleton INSTANCE = new Singleton();
	}
	public static final Singleton getInstance4(){
		return LazyHolder.INSTANCE;
	}
}

/**
 * ����ģʽ
 * @author itaiti
 * @email fei.zhang@mtime.com
 * @date 2017��1��17�� ����6:30:59
 * @version V1.0
 */
class Singleton2{
	private static Singleton2 instance = new Singleton2();
	private Singleton2(){}
	public static Singleton2 getInstance(){
		return instance;
	}
}
