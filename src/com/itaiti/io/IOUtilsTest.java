package com.itaiti.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

public class IOUtilsTest {
	
	public void contentEqualsTest() throws IOException {
		InputStream in = new URL("http://www.apache.org").openStream();
		InputStream in2 = new URL("http://www.apache.org").openStream();
		/**
		 * IOUtils.contentEquals(InputStream input1, InputStream input2) 英文：
		 * Compare the contents of two Streams to determine if they are equal or
		 * not. 翻译： 比较两个输入流的内容是否相等
		 */
		try {
			// IOUtils.toString将缓冲区的内容以utf-8的编码方式以字符串的形式输出
			// System.out.println(IOUtils.toString(in,"UTF-8"));
		} finally {
			IOUtils.closeQuietly(in);
		}
		System.out.println(IOUtils.contentEquals(in, in2));

		System.out.println("-----------------------------------------------------------------------");
	}
	
	/**
	 * IOUtils.copy(InputStream input, OutputStream output) 英文： Copy bytes
	 * from an InputStream to an OutputStream. 翻译： 将字节从
	 * InputStream复制到OutputStream中
	 * @throws IOException 
	 */
	public void closeQuietlyTest() throws IOException {
		InputStream in = new URL("http://www.apache.org").openStream();
		InputStream in2 = new URL("http://www.apache.org").openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(in2, out);
		System.out.println(out.size());
		// System.out.println(out.toString("UTF-8"));
		// IOUtils.closeQuietly(in);
		// IOUtils.closeQuietly(out);
		System.out.println("-----------------------------------");
	}
	
	/**
	 * IOUtils.copyLarge(InputStream input, OutputStream output); 英文 Copy
	 * bytes from a large (over 2GB) InputStream to an OutputStream. 翻译：
	 * 将字节超过2G的字节输入流复制到输出流中
	 * @throws IOException 
	 */
	public void copyLargeTest() throws IOException {
		InputStream in2 = new URL("http://www.apache.org").openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copyLarge(in2, out);
		// System.out.println(out.toString("UTF-8"));
		System.out.println("------------------------------------------------------------");
	}
	
	/**
	 * IOUtils.lineIterator(InputStream input, Charset encoding) 英文 Return
	 * an Iterator for the lines in an InputStream, using the character
	 * encoding specified (or default encoding if null). 翻译
	 * 返回一个装有输入字节行数的Iterator对象，使用特定的字符编码（如果没用声明的话则用默认编码）
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public void lineIteratorTest() throws MalformedURLException, IOException {
		InputStream in = new URL("http://www.apache.org").openStream();
		InputStream in3 = new URL("http://www.apache.org").openStream();
		try {
			LineIterator it = IOUtils.lineIterator(in3, "UTF-8");
			System.out.println(it.hasNext());
			if (it.hasNext()) {
				String line = it.nextLine();
				// System.out.println(line);
			}
		} finally {
			IOUtils.closeQuietly(in);
		}
		System.out.println("------------------------------------------------------------------");
	}
	
	/**
	 * IOUtils.read(InputStream input, byte[] buffer) 英文： Read bytes from an
	 * input stream. 中文：从输入流中读取字节(通常返回输入流的字节数组的长度)
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void readTest() throws MalformedURLException, IOException {
		InputStream in4 = new URL("http://www.apache.org").openStream();
		byte[] buffer = new byte[100000];
		System.out.println(IOUtils.read(in4, buffer));
		System.out.println("------------------------------------------------");
	}
	
	/**
	 * IOUtils.readLines(InputStream input, Charset encoding) 英文： Get the
	 * contents of an InputStream as a list of Strings, one entry per line,
	 * using the specified character encoding. 翻译：
	 * 获得输入流的内容放回一个List<String>类型的容器，每一行为这个容器的一个入口，使用特定的字符集（如果为空就使用默认的字符集）
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void readLinesTest() throws MalformedURLException, IOException {
		InputStream in5 = new URL("http://www.apache.org").openStream();
		List<String> list = IOUtils.readLines(in5, "UTF-8");
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String s = iter.next();
			System.out.println(s);
		}
		System.out.println("--------------------------------------------------------------");
	}
}
