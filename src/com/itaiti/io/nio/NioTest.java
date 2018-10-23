package com.itaiti.io.nio;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class NioTest {
	
	@Test
	public void test() {
		try {
			System.out.println(InetAddress.getByName(""));
			System.out.println(InetAddress.getByName(null));
			System.out.println(InetAddress.getByName("www.163.com"));
			System.out.println(InetAddress.getByName("www.mtime.com"));
			System.out.println(InetAddress.getByName("www.qq.com"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
