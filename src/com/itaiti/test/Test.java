package com.itaiti.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Test {
	
	@org.junit.Test
	public void testStr() {
		/*java.lang.String[] descriptorData = {
			      "\n\rContact.proto\032\014Common.proto\"\322\001\n\007Contac" +
			      "t\022\021\n\tcontactid\030\001 \002(\005\022\022\n\nsupplierid\030\002 \002(\005" +
			      "\022\023\n\013contacttype\030\003 \001(\005\022\023\n\013contactname\030\004 \001" +
			      "(\t\022\020\n\010jobtitle\030\005 \001(\t\022\016\n\006mobile\030\006 \001(\t\022\r\n\005" +
			      "email\030\007 \001(\t\022\021\n\ttelephone\030\010 \001(\t\022\013\n\003fax\030\t " +
			      "\001(\t\022\016\n\006remark\030\n \001(\t\022\025\n\rcontactstatus\030\013 \002" +
			      "(\005\"\310\001\n\020InsertContactReq\022\022\n\nsupplierid\030\002 " +
			      "\002(\005\022\023\n\013contacttype\030\003 \001(\005\022\023\n\013contactname\030" +
			      "\004 \001(\t\022\020\n\010jobtitle\030\005 \001(\t\022\016\n\006mobile\030\006 \001(\t\022" +
			      "\r\n\005email\030\007 \001(\t\022\021\n\ttelephone\030\010 \001(\t\022\013\n\003fax",
			      "\030\t \001(\t\022\016\n\006remark\030\n \001(\t\022\025\n\rcontactstatus\030" +
			      "\013 \002(\005\"[\n\031GetContactBySupplierIdRsq\022\031\n\007Co" +
			      "ntact\030\001 \003(\0132\010.Contact\022#\n\014InvokeResult\030\002 " +
			      "\002(\0132\r.InvokeResultB+\n\033com.mtime.ecommerc" +
			      "e.wms.dtoB\014ContactOuter"
			    };
		System.out.println(descriptorData[0]);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("www.mtime.com");
		
		StringBuilder builder = new StringBuilder();
		builder.append("www.mtime.com");*/
		
		/*Long l = 1000l;//new Long(100);
		Long l2 = 1000l;//new Long(100);
		System.out.println(l==l2);
		long l3 = 1000;
		long l4 = 1000;
		System.out.println(l3==l4);
		Integer i = 1;//new Integer(1);
		Integer i2 = 1;new Integer(1);
		System.out.println(i==i2);*/
		//可用的处理器
		//System.out.println(Runtime.getRuntime().availableProcessors());
		
		List<Integer> list = new ArrayList<>();
		list.size();
		int i = 1;
		/*System.out.println(i++);
		System.out.println(++i);*/
		
	}
	
	@org.junit.Test
	public void bitTest(){
		int ssize = 8;
		ssize >>= 4;
		System.out.println(ssize);
	}
	
	private static String qrcodeUrl="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=ACCESS_TOKEN";
	
	public void strAppend(int i){
		 System.out.println(qrcodeUrl.replace("ACCESS_TOKEN", String.valueOf(i)));
	}
	
	@org.junit.Test
	public void strTest(){
		/*for(int i=0; i<3; i++){
			strAppend(i);
		}*/
		//System.out.println(System.currentTimeMillis());
		/*System.out.println((int)1.9);
		System.out.println(Math.round(1.4));
		System.out.println(Math.ceil(0.1));*/
		for(int i=0; i<10; i++) {
			System.out.println("i % 3 = "+(i%3));
		}
	}
	
	//或运算 |
	/*
		0 ^ 1 得 1
		1 ^ 1 得 1
		0 ^ 0 得 0
		1 ^ 0 得 1
	*/
	@org.junit.Test
	public void orTest() {
		int a=1;
		int b=64;
		int v = (b|a);
		System.out.println(v);
		//	00000001	1
		//	    |	        或运算	
		//	01000000	64
		//	01000001	65
		
			
	}
	
	//异或运算 ^	相同为0，相异为1
	/*
		0 ^ 1 得 1
		1 ^ 1 得 0
		0 ^ 0 得 0
		1 ^ 0 得 1
	*/
	@org.junit.Test
	public void sTest() {
		System.out.println(64^1);
		//	00000001	1
		//	   ^	        异或运算	
		//	01000000	64
		//	01000001	65
		
		/*
			1001001010101
			^
			1010111101001
			0011110111100
		*/
	}
	
	@org.junit.Test
	public void exchange() {
		int a = 55;
		int b = 66;
		a=a^b;
		System.out.println("a="+a);
		b=a^b;
		System.out.println("b="+b);
		a=a^b;
		System.out.println("a="+a);
	}
	
	@org.junit.Test
	public void mTest() {
		int a = 4;
		int id = 21280739;//21280744;//1000004
		int v = id % a;
		System.out.println(v);
	}
	
	@org.junit.Test
	public void forTest() {
		for(int i=0; i<10; i++) {
			if(i==3) {
				continue;
			}
			System.out.println(i);
		}
	}
	
	@org.junit.Test
	public void ifElseTest() {
		int a = 1;
		if(a == 1) {
			System.out.println(a);
		} else if(a == 2) {
			System.out.println(222);
		}else {
			System.out.println(123);
		}
	}
	
	@org.junit.Test
	public void mathTest() {
		double a = 56;
		double b = 49;
		double c = 47;
		double d = 41;
		double avg = (a+b+c+d)/4;
		System.out.println("平均数="+avg);
		double power = (Math.pow(a-avg, 2) + Math.pow(b-avg, 2) + Math.pow(c-avg, 2) + Math.pow(d-avg, 2))/4;
		System.out.println("方差="+power);
		double sqrt = Math.sqrt(power);
		System.out.println("标准差="+sqrt);
		System.out.println("最终结果="+(sqrt/avg)*100);
		
		System.out.println(Math.pow(-2,2));
		double v = 60.0625+0.5625+1.5625+52.1284;
		System.out.println(v/4);
		
	}
	
	@org.junit.Test
	public void objectsTest() {
		String a = "mtime";
		String b = "mtime";
		System.out.println(Objects.equals(a, b));
	}
}
