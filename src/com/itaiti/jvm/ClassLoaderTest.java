package com.itaiti.jvm;

public class ClassLoaderTest {
	
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader loader = HelloWorld.class.getClassLoader();
		System.out.println(loader);
		System.out.println(loader.getParent());
		System.out.println(loader.getParent().getParent());
		
		//使用ClassLoader.loadClass()来加载类，不会执行初始化块 
//        loader.loadClass("com.mtime.jvm.TestOne");
        //loader.getSystemClassLoader().loadClass("com.mtime.jvm.TestOne");
        //使用Class.forName()来加载类，默认会执行初始化块 
//        Class.forName("com.mtime.jvm.TestOne"); 
        //使用Class.forName()来加载类，并指定ClassLoader，false:初始化时不执行静态块,true:吃屎话是执行静态块 
        Class.forName("com.mtime.jvm.TestOne", false, loader); 
	}
}
