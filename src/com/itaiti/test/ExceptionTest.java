package com.itaiti.test;

import org.junit.Test;


public class ExceptionTest {
	
	@Test
	public void exceptionTest() throws Exception{
		for(int i=0; i<3; i++){
			System.out.println("------"+i);
			throw new Exception("参数越界");
    		/*try {
    			System.out.println("------"+i);
    			if(i == 1){
    				return;
    			}
    			throw new Exception("参数越界");
    			
    		} catch(Exception e){
    			System.out.println("------"+e);
    		}*/
    		
    	}
	}
}
