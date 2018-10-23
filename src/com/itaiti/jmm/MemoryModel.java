package com.itaiti.jmm;

import java.io.File;

public class MemoryModel {
	
	private int count;
    private boolean stop;
    public void initCountAndStop() {
        count = 1;
        stop = false;
    }
    public void doLoop() {
        while(!stop) {
            count++;
        }
    }
    public void printResult() {
        System.out.println(count);
        System.out.println(stop);
    }
    
    public static void main(String[] args) {
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(System.getProperty("java.security.manager"));
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(File.pathSeparator);
		
		ClassLoader loader = MemoryModel.class.getClassLoader();
		System.err.println(loader);
		while (loader != null) {
			loader = loader.getParent();
			System.err.println(loader);
		}
	}
}
