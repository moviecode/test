package com.itaiti.test;

import org.junit.Test;


public class ExceptionTest {
	
	@Test
	public void exceptionTest() throws Exception{
		for(int i=0; i<3; i++){
			System.out.println("------"+i);
			throw new Exception("����Խ��");
    		/*try {
    			System.out.println("------"+i);
    			if(i == 1){
    				return;
    			}
    			throw new Exception("����Խ��");
    			
    		} catch(Exception e){
    			System.out.println("------"+e);
    		}*/
    		
    	}
	}
}
