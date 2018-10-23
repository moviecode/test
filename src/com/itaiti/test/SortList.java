package com.itaiti.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SortList<E> {
	
	public void Sort(List<E> list, final String method, final String sort) {
		Collections.sort(list, new Comparator<E>() {
			public int compare(Object a, Object b) {
				int ret = 0;
				try {
					Method m1 = ((E) a).getClass().getMethod(method, null);
					Method m2 = ((E) b).getClass().getMethod(method, null);
					if (sort != null && "desc".equals(sort))// 倒序
						ret = m2.invoke(((E) b), null).toString().compareTo(m1.invoke(((E) a), null).toString());
					else// 正序
						ret = m1.invoke(((E) a), null).toString().compareTo(m2.invoke(((E) b), null).toString());
				} catch (NoSuchMethodException ne) {
					System.out.println(ne);
				} catch (IllegalAccessException ie) {
					System.out.println(ie);
				} catch (InvocationTargetException it) {
					System.out.println(it);
				}
				return ret;
			}
		});
	}
	
	public static void main(String[] args) throws ParseException {
		List<UserInfo> list = new ArrayList<UserInfo>();
	    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	    list.add(new UserInfo(3,"b",formater.parse("1980-12-01"),11));
	    list.add(new UserInfo(1,"c",formater.parse("1980-10-01"),30));
	    list.add(new UserInfo(2,"a",formater.parse("1973-10-01"),11));
	    System.out.println("-------原来序列-------------------");
	    for(UserInfo user : list){
	      System.out.println(user.toString());
	    }
	    //调用排序通用类
	    SortList<UserInfo> sortList = new SortList<UserInfo>();
	    //按userId排序
	    sortList.Sort(list, "getUserId", "desc");
	    System.out.println("--------按userId倒序------------------");
	    for(UserInfo user : list){
	      System.out.println(user.toString());
	    }
	    //按username排序
	    sortList.Sort(list, "getUsername", null);
	    System.out.println("---------按username排序-----------------");
	    for(UserInfo user : list){
	      System.out.println(user.toString());
	    }
	    //按birthDate排序
	    sortList.Sort(list, "getBirthDate", null);
	    System.out.println("---------按birthDate排序-----------------");
	    for(UserInfo user : list){
	      System.out.println(user.toString());
	    }
	}
}

class UserInfo{
	private java.lang.Integer userId;
	private java.lang.String username;
	private java.util.Date birthDate;
	private java.lang.Integer age;
	
	public UserInfo(Integer userId, String username, Date birthDate, Integer age) {
		super();
		this.userId = userId;
		this.username = username;
		this.birthDate = birthDate;
		this.age = age;
	}
	public java.lang.Integer getUserId() {
		return userId;
	}
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	public java.lang.String getUsername() {
		return username;
	}
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	public java.util.Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(java.util.Date birthDate) {
		this.birthDate = birthDate;
	}
	public java.lang.Integer getAge() {
		return age;
	}
	public void setAge(java.lang.Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return this.userId + "-" + this.username + "-" + this.birthDate + "-" + this.age;
	}
}
