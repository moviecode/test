package com.itaiti.thread;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.google.common.collect.Lists;

public class FileReadTask implements Callable<FileConfig> {
	
	private File f;
	private FileConfig fc;
	
	public FileReadTask(File fe, FileConfig fileConfig) {
		super();
		this.f = fe;
		this.fc = fileConfig;
	}
	
	@Override
	public FileConfig call() throws Exception {
		LineIterator it = null;
		List<String> list = Lists.newArrayList();
		try {
			it = FileUtils.lineIterator(f, "utf-8");
			while(it.hasNext()) {
				list.add(it.nextLine());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(it != null) {
				LineIterator.closeQuietly(it);
			}
		}
		fc.setUserIds(list);
		return fc;
	}

}
