package com.itaiti.jvm;

public class ClassLoaderTest {
	
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader loader = HelloWorld.class.getClassLoader();
		System.out.println(loader);
		System.out.println(loader.getParent());
		System.out.println(loader.getParent().getParent());
		
		//ʹ��ClassLoader.loadClass()�������࣬����ִ�г�ʼ���� 
//        loader.loadClass("com.mtime.jvm.TestOne");
        //loader.getSystemClassLoader().loadClass("com.mtime.jvm.TestOne");
        //ʹ��Class.forName()�������࣬Ĭ�ϻ�ִ�г�ʼ���� 
//        Class.forName("com.mtime.jvm.TestOne"); 
        //ʹ��Class.forName()�������࣬��ָ��ClassLoader��false:��ʼ��ʱ��ִ�о�̬��,true:��ʺ����ִ�о�̬�� 
        Class.forName("com.mtime.jvm.TestOne", false, loader); 
	}
}
