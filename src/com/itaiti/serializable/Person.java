package com.itaiti.serializable;

import java.io.Serializable;

import lombok.Data;

@Data
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4842075616350068144L;
	
	private Integer age;
	private String name;
	private String sex;
}
