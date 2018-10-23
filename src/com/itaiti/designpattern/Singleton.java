package com.itaiti.designpattern;

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

/**
 * volatile��ʽ
 * @author itaiti
 * @email fei.zhang@mtime.com
 * @date 2018��2��23�� ����5:56:13
 * @version V1.0
 */
class Singleton3 {
	private volatile static Singleton3 instance;
	private Singleton3() {
		
	}
	public static Singleton3 getInstance() {
		if(instance == null) {
			synchronized(Singleton3.class) {
				if(instance == null) {
					instance = new Singleton3();
				}
			}
		}
		return instance;
	}
}

/**
 * �ڲ��෽ʽ
 * @author itaiti
 * @email fei.zhang@mtime.com
 * @date 2018��2��23�� ����6:01:30
 * @version V1.0
 */
class Singleton4 {
	//��ʽ�ģ���̬����
	private static class LazyHolder{
		private static final Singleton4 INSTANCE = new Singleton4();
	}
	public static final Singleton4 getInstance4(){
		return LazyHolder.INSTANCE;
	}
}
