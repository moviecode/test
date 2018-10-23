package com.itaiti.thread;

import java.util.List;

import lombok.Data;

@Data
public class FileConfig {
	private String couponId;
	private String type;
	private List<String> userIds;
}
