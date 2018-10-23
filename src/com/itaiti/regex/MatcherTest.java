package com.itaiti.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class MatcherTest {
	
	/*
	1.正则表达式基本语法
	两个特殊的符号'^'和'$'。他们的作用是分别指出一个字符串的开始和结束。例子如下：
	"^The"：表示所有以"The"开始的字符串（"There"，"The cat"等）；
	"of despair$"：表示所以以"of despair"结尾的字符串；
	"^abc$"：表示开始和结尾都是"abc"的字符串——呵呵，只有"abc"自己了；
	"notice"：表示任何包含"notice"的字符串。
	象最后那个例子，如果你不使用两个特殊字符，你就在表示要查找的串在被查找串的任意部分——你并
	不把它定位在某一个顶端。
	其它还有'*'，'+'和'?'这三个符号，表示一个或一序列字符重复出现的次数。它们分别表示“没有或
	更多”，“一次或更多”还有“没有或一次”。下面是几个例子：
	"ab*"：表示一个字符串有一个a后面跟着零个或若干个b。（"a", "ab", "abbb",……）；
	"ab+"：表示一个字符串有一个a后面跟着至少一个b或者更多；
	"ab?"：表示一个字符串有一个a后面跟着零个或者一个b；
	"a?b+$"：表示在字符串的末尾有零个或一个a跟着一个或几个b。
	你也可以使用范围，用大括号括起，用以表示重复次数的范围。
	"ab{2}"：表示一个字符串有一个a跟着2个b（"abb"）；
	"ab{2,}"：表示一个字符串有一个a跟着至少2个b；
	"ab{3,5}"：表示一个字符串有一个a跟着3到5个b。
	请注意，你必须指定范围的下限（如："{0,2}"而不是"{,2}"）。还有，你可能注意到了，'*'，'+'和
	'?'相当于"{0,}"，"{1,}"和"{0,1}"。
	还有一个'¦'，表示“或”操作：
	"hi¦hello"：表示一个字符串里有"hi"或者"hello"；
	"(b¦cd)ef"：表示"bef"或"cdef"；
	"(a¦b)*c"：表示一串"a""b"混合的字符串后面跟一个"c"；
	'.'可以替代任何字符：
	"a.[0-9]"：表示一个字符串有一个"a"后面跟着一个任意字符和一个数字；
	"^.{3}$"：表示有任意三个字符的字符串（长度为3个字符）；
	方括号表示某些字符允许在一个字符串中的某一特定位置出现：
	"[ab]"：表示一个字符串有一个"a"或"b"（相当于"a¦b"）；
	"[a-d]"：表示一个字符串包含小写的'a'到'd'中的一个（相当于"a¦b¦c¦d"或者"[abcd]"）；
	"^[a-zA-Z]"：表示一个以字母开头的字符串；
	"[0-9]%"：表示一个百分号前有一位的数字；
	",[a-zA-Z0-9]$"：表示一个字符串以一个逗号后面跟着一个字母或数字结束。
	你也可以在方括号里用'^'表示不希望出现的字符，'^'应在方括号里的第一位。（如："%[^a-zA-Z]%"表
	示两个百分号中不应该出现字母）。
	为了逐字表达，你必须在"^.$()¦*+?{\"这些字符前加上转移字符'\'。
	请注意在方括号中，不需要转义字符。
	*/
	
	
	
	
	//<img data-s="300,640" data-type="jpeg" data-src="https://mmbiz.qpic.cn/mmbiz_jpg/e4BqjHy5plWpfKq4Q5EJ1mgndJZI7UVDAlKIWfqnyKZicUbzLC8vR0xvhwc9kxpvUgdw3x4gH4WVsCIOvJkO0pQ/0?wx_fmt=jpeg" class="" data-ratio="0.628125" data-w="1280"  />
	
	//<img data-s="300,640" data-type="jpeg" data-src="https://mmbiz.qpic.cn/mmbiz_jpg/kO0pQ/0?wx_fmt=jpeg" class="" data-ratio="0.628125" data-w="1280"  />
	//<img src="https://mmbiz.qpic.cn/mmbiz_jpg/e4BqjHy5plWpfKq4Q5EJ1mgndJZI7UVDAlKIWfqnyKZicUbzLC8vR0xvhwc9kxpvUgdw3x4gH4WVsCIOvJkO0pQ/0?wx_fmt=jpeg"/>
	/**
	 * 匹配替换，从<img开始匹配任意非>的字符，data-src可以出现一次，然后继续匹配非>的字符，直到出现>停止,最后替换调这些字符
	 * [^>]表示不是“>”的字符，*表示重复零次或更多次，这个意思是非“>”的字符可以有一个或多个，也可以没有
	 * +data-src:匹配一次data-src字符串
	 * 
	 * 用()括起来，表示分组
	 * @throws IOException
	 */
	@Test
	public void imgSrcTest() throws IOException {
		//String body = "\"\"";
		String body = "<img data-s=\"300,640\" data-type=\"jpeg\" data-src=\"https://mmbiz.qpic.cn/mmbiz_jpg/kO0pQ/0?wx_fmt=jpeg\" class=\"test\" data-ratio=\"0.628125\" data-w=\"1280\" />";
		System.out.println("body:"+body);
		Matcher matcherImg = Pattern.compile("<img[^>]+data-src=\"([^\"]+)\"[^>]+class=\"([^\"]+)\"[^>]*>").matcher(body);
		//System.out.println(matcherImg.groupCount());
		System.out.println(matcherImg.matches());
		System.out.println(matcherImg.group());
		System.out.println(matcherImg.group(0));
		System.out.println(matcherImg.group(1));
		System.out.println(matcherImg.group(2));
		//group()或group(0)的值就是body
		//group(1)的值为：data-src中的值
		// 相当于把body全部替换，$1取group(1)中的值
        body = matcherImg.replaceAll("<img src=\"$1\"/>");
        System.out.println("************************************");
        System.out.println("body:"+body);
	}
	
	@Test
	public void test() {
		// 匹配已the开始的字符
		String the = "the1";
		Matcher matcher = Pattern.compile("^the").matcher(the);
		System.out.println(matcher.matches());
		//把the替换成111
		System.out.println(matcher.replaceAll("111"));;
	}
	
	public String getBody() {
		StringBuffer sb  = new StringBuffer();
		try {
			FileReader fr = new FileReader("D:\\Mtime\\workspace\\test\\src\\com\\mtime\\regex\\body.txt");
			BufferedReader br = new BufferedReader(fr);
			String s = null;
			while((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	@Test
	public void test22() {
		String videoId = "20170918";
		String posterUrl = "http://www.mtime.com";
		//String body = "data-video-id=\"" + videoId + "\" poster=\"\"";
		String body = "< data-video-id=\"" + videoId + "\" >";
		System.out.println("1: "+body);
		Matcher matcherPosterNull =
                Pattern.compile("data-video-id=\"" + videoId + "\" (?!poster)").matcher(body);
        body = matcherPosterNull
                .replaceAll("data-video-id=\"" + videoId + "\" poster=\"" + posterUrl + "\" ");
        System.out.println("2: "+body);
        Matcher matcherPosterEmpty =
                Pattern.compile("data-video-id=\"" + videoId + "\" poster=\"\"").matcher(body);
        body = matcherPosterEmpty
                .replaceAll("data-video-id=\"" + videoId + "\" poster=\"" + posterUrl + "\" ");
        
        System.out.println("3: "+body);
	}
	
	@Test
	public void test23() {
		/*String str = "123abc";
		Matcher matcher = Pattern.compile("\\d{3}(?!\\d)").matcher(str);
		System.out.println(matcher.find());
		System.out.println(matcher.replaceAll("io"));*/
	}
	
	@Test
	public void testSpecialCharacters() {
		String source = "abc.123!@#时光";
		String nick_name = source.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]", "").trim();
		System.out.println(nick_name);
	}
	
	/**
	 * 获取三位随机数 且不含4
	 * @return
	 */
	@Test
	public void random(){
		Random random = new Random();
		int num;
		while(true){
			num = random.nextInt(100);
			if(!(num+"").contains("4")) break ;
		}
		String str = String.format("%0"+3+"d", num);
		System.out.println(num);
		System.out.println("%0"+3+"d");
		System.out.println(str);
	}
}
