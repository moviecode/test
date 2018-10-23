package com.itaiti.verifycode;

import lombok.Data;

@Data
public class CodeItem {
	public String Code;
	public long AddTime;
	
	public CodeItem(String code) {
        Code = code;
        AddTime = System.currentTimeMillis();
    }
}
