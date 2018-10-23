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
		Person p = deserializePerson();//������Perons����
		System.out.println(String.format("name=%s,age=%s,sex=%s",
		p.getName(), p.getAge(), p.getSex()));
	}
	
	public static void serializePerson() {
		Person person = new Person();
		person.setAge(30);
		person.setName("itaiti");
		person.setSex("��");
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File("d:/test/Person.txt")));
			out.writeObject(person);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Person�������л��ɹ���");
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
		 System.out.println("Person�������л��ɹ���");
		 return null;
	}
}
