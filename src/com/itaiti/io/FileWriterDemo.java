package com.itaiti.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class FileWriterDemo {

	/*public static void main(String[] args) throws IOException {
		//write()
		read();
	}*/
	
	public static void write()throws IOException{
		File file = new File("D:\\test\\demo.txt");
		if(file.exists()){
			file.createNewFile();
		}
		//true 杩藉姞
		FileWriter fr = new FileWriter(file, true);
		fr.append("\n");
		fr.append("");
		//fr.flush();
		fr.append("\n");
		fr.append("www.mtime.com");
		fr.close();
	}
	
	public static void read() throws IOException{
		FileReader fr = new FileReader("D:\\test\\rediskey.txt");
		BufferedReader br = new BufferedReader(fr);
		StringBuffer keys = new StringBuffer();
		String s;
		while((s = br.readLine()) != null){
			//System.out.println(s);
			keys.append(s.split(" ")[1]).append(" ");
		}
		System.out.println(keys.toString());
		br.close();
	}
	
	// 21435
	@Test 
	public void test() {
		String s = "5) TM_3185683";
		String[] arr = s.split(" ");
		System.out.println(arr[0]);
		System.out.println(arr[1]);
	}
	
	@Test
	public void strSub() {
		String str = "2018-04-16 23:09:30 ip:220.202.99.119鍦�0绉掑唴娉ㄥ唽maxCount=1娆★紝瓒呴檺锛屽姞鍏ラ粦鍚嶅崟锛岀姝㈡敞鍐岋紒";
		int start = str.indexOf("ip:");
		int end = str.indexOf("鍦�0绉�");
		System.out.println(str.startsWith("2018-04-15"));
		System.out.println(start);
		System.out.println(end);
		System.out.println(str.substring(start+3, end));
	}
	
	@Test
	public void ipBlackList() throws IOException {
		FileReader fr = new FileReader("D:\\test\\榛戝悕鍗旾P璁板綍.txt");
		BufferedReader br = new BufferedReader(fr);
		StringBuffer ips = new StringBuffer();
		Set<String> set = Sets.newHashSet();
		String s;
		while((s = br.readLine()) != null){
			//System.out.println(s);
			//keys.append(s.split(" ")[1]).append(" ");
			if(s != null && s.trim().length() > 0) {
				if(s.startsWith("2018-04-16") || s.startsWith("2018-04-15")) {
					set.add(getIp(s));
				}
			}
		}
		br.close();
		System.out.println("ip size: " + set.size());
		//System.out.println(set);
		set.forEach(p -> {
			ips.append("\"").append(p).append("\"").append(",");
		});
		System.out.println("ips:" + ips.toString());
	}
	
	public static String getIp(String str) {
		int start = str.indexOf("ip:");
		int end = str.indexOf("鍦�0绉�");
		return str.substring(start+3, end);
	}

}
