package com.itaiti.test;

/**
 * 
 * @author itaiti
 * @email itaiti@163.com
 * @date 2016年9月5日 下午2:40:51
 * @version V1.0
 */
public class HelloWorld {

	public static void main(String[] args) {
		/*
		 * System.out.println("HelloWorld!");
		 * System.out.println("100001001=\u6587\u4EF6\u4E0D\u5B58\u5728");
		 * System.out.println("100001002=\u6587\u4EF6\u8BFB\u53D6\u5931\u8D25");
		 * System.out.println("100002001=\u6DFB\u52A0\u8BB0\u5F55\u5931\u8D25");
		 * System.out.println("100002002=\u66F4\u65B0\u8BB0\u5F55\u5931\u8D25");
		 * System.out.println("100002003=\u5220\u9664\u8BB0\u5F55\u5931\u8D25");
		 * System.out.println("200100001=\u53C2\u6570\u4E0D\u5408\u6CD5");
		 * System.out.println(
		 * "300001001=\u6279\u6B21\u5E93\u5B58\u4E0D\u5B58\u5728");
		 * System.out.println("300001002=\u6279\u6B21\u5B9E\u7269\u4E0D\u8DB3");
		 */
		/*final String gbString = "中国";
		String dataStr = gbEncoding(gbString);
		// System.out.println("\u4e2d\u56fd");
		System.out.println(decodeUnicode(dataStr));
		System.out.println(decodeUnicode2(dataStr));*/
		Long id = null;
		if(0 != id) {
			
		}

	}

	public static String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		System.out.println("unicodeBytes is: " + unicodeBytes);
		return unicodeBytes;
	}

	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	public static String decodeUnicode2(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}

		return outBuffer.toString();
	}
}
