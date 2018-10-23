package com.itaiti.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestObjSerializeAndDeserialize {
	
	private static ObjectInputStream in;

	public static void main(String[] args) {
		serializePerson();
		Person p = deserializePerson();//反序列Perons对象
		System.out.println(String.format("name=%s,age=%s,sex=%s",
		p.getName(), p.getAge(), p.getSex()));
	}
	
	public static void serializePerson() {
		Person person = new Person();
		person.setAge(30);
		person.setName("itaiti");
		person.setSex("男");
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File("d:/test/Person.txt")));
			out.writeObject(person);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Person对象序列化成功！");
	}
	
	public static Person deserializePerson() {
		try {
			in = new ObjectInputStream(new FileInputStream(new File("d:/test/Person.txt")));
			Person person = (Person) in.readObject();
			return person;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 System.out.println("Person对象反序列化成功！");
		 return null;
	}
}
