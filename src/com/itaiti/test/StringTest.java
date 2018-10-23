package com.itaiti.test;

import org.junit.Test;

import lombok.Data;

public abstract class StringTest {
	
	public abstract void builderParts();
	
	@Test
	public void hashCodeTest() {
		String s1 = "sss";
		String s2 = "sss";
		String s3 = new String("sss");
		System.out.println("s1==s2:"+(s1==s2));// true
		System.out.println("s2==s3:"+(s2==s3));// false
		System.out.println("s1.equals(s2):"+s1.equals(s2));//true
		System.out.println("s2.equals(s3)"+s2.equals(s3));//true
		
		User user = new User();
		user.setUserId(1);
		user.setPwd("1234");
		user.setSex(1);
		user.setUserName("9527");
		
		User user2 = new User();
		user2.setUserId(1);
		user2.setPwd("1234");
		user2.setSex(1);
		user2.setUserName("9527");
		System.out.println("hashCode:"+(user.hashCode()==user2.hashCode()));// true
		System.out.println("equals:"+user.equals(user2));//false
		
		
	}
	
	@Test
	public void test() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<10; i++) {
			if(i>0 && i<10) {
				sb.append(",");
			}
			sb.append("test_"+i);
		}
		System.out.println(sb.toString());
	}

	/*public static void main(String[] args) {
		// String str = "时光对话";
		// System.out.println(str.getBytes().length);
		// System.out.println(getLength(str));
		// System.out.println(1&0);

		StringBuilder sb = new StringBuilder(10);
		System.out.println(sb.length());
		sb.append("sss123");
		System.out.println(sb.length());
		// sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}*/

	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param String
	 *            s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5
	 * 
	 * @param String
	 *            s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static double getLength(String s) {
		double valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		// 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		for (int i = 0; i < s.length(); i++) {
			// 获取一个字符
			String temp = s.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				// 中文字符长度为1
				valueLength += 1;
			} else {
				// 其他字符长度为0.5
				valueLength += 0.5;
			}
		}
		// 进位取整
		return Math.ceil(valueLength);
	}
	
	@Test
	public void timeCount() throws InterruptedException{
		long start = System.currentTimeMillis();
		Thread.sleep(3000);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
	}
	
	@Test
	public void timeStamp(){
		System.out.println(System.currentTimeMillis());
	}
	
	@Test
	public void stringFormatTest(){
		String value="https://api.weibo.com/2/users/show.json?access_token=%s&amp;uid=%s";
		System.out.println(value);
		System.out.println(String.format(value, "access_token", "uid"));
	}
	
	@Test
	public void testA() {
		//System.out.println(16|1);
		//System.out.println(16^1);
		int x=(int)(Math.random()*8*1000*10000);
		System.out.println(x);
	}
	
	@Test
	public void equalsIgnoreCase() {
		String pwd = "abc123";
		String pwdAgain = "AbC123";
		System.out.println(pwd.equalsIgnoreCase(pwdAgain.toUpperCase()));
		int i=0;
		if(i==0) {
			System.out.println("i===");
		} else if(i>=0) {
			System.out.println("i>=0");
		}else {
			System.out.println("else");
		}
	}
	
	@Test
	public void intern() {
		/*String s1 = "abc";
		String s2 = new String("abc");
		s2.intern();
		System.out.println(s1 == s2);
		System.out.println(s2 == s2.intern());
		System.out.println(s1 == s1.intern());*/
		//System.out.println(101/2);
	}
	
}

@Data
class User {
	private int userId;
	private String userName;
	private String pwd;
	private int sex;
	
	@Override
	public int hashCode() {
		return this.userId;
	}
}
