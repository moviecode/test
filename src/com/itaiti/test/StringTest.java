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
		// String str = "ʱ��Ի�";
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
	 * �ж��ַ����Ƿ�Ϊ��
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
	 * �õ�һ���ַ����ĳ���,��ʾ�ĳ���,һ�����ֻ��պ��ĳ���Ϊ2,Ӣ���ַ�����Ϊ1
	 * 
	 * @param String
	 *            s ��Ҫ�õ����ȵ��ַ���
	 * @return int �õ����ַ�������
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
	 * �õ�һ���ַ����ĳ���,��ʾ�ĳ���,һ�����ֻ��պ��ĳ���Ϊ1,Ӣ���ַ�����Ϊ0.5
	 * 
	 * @param String
	 *            s ��Ҫ�õ����ȵ��ַ���
	 * @return int �õ����ַ�������
	 */
	public static double getLength(String s) {
		double valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		// ��ȡ�ֶ�ֵ�ĳ��ȣ�����������ַ�����ÿ�������ַ�����Ϊ2������Ϊ1
		for (int i = 0; i < s.length(); i++) {
			// ��ȡһ���ַ�
			String temp = s.substring(i, i + 1);
			// �ж��Ƿ�Ϊ�����ַ�
			if (temp.matches(chinese)) {
				// �����ַ�����Ϊ1
				valueLength += 1;
			} else {
				// �����ַ�����Ϊ0.5
				valueLength += 0.5;
			}
		}
		// ��λȡ��
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
