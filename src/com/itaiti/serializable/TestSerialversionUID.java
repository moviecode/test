package com.itaiti.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * cause by https://www.cnblogs.com/xdp-gacl/p/3777987.html
 * @author itaiti
 * @email fei.zhang@mtime.com
 * @date 2018��10��23�� ����5:40:55
 * @version V1.0
 */
public class TestSerialversionUID {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		//serializeCustomer();
		Customer customer = deserializeCustomer();
		System.out.println(customer);
	}
	
	/**
	 * ���л�Customer����
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void serializeCustomer() throws FileNotFoundException, IOException {
		Customer customer = new Customer("mtime", 12/*, "��"*/);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("D:/test/customer.txt")));
		out.writeObject(customer);
		System.out.println("Customer�������л��ɹ���");
		out.close();
	}
	
	/**
	 * �����л�Customer����
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Customer deserializeCustomer() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("D:/test/customer.txt")));
		Customer customer = (Customer)in.readObject();
		System.out.println("Customer�������л��ɹ���");
		return customer;
	}
}

class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1737492342885291430L;
	private String name;
	private Integer age;
//	private String sex;	
	public Customer(String name, Integer age/*, String sex*/) {
		this.name = name;
		this.age = age;
//		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return "name="+ name + ", age=" + age; 
	}
}
