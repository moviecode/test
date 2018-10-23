package com.itaiti.webmagic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.itaiti.webmagic.pageprocessor.WxAccountPageProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * simple example
 * @version V1.0
 */
public class WebMagicDemo {
	
	private static String WX_ACCOUNT_CRAWL_URL = "http://weixin.sogou.com/weixin?type=1&query=%s";
	
	public void firstWebMagic(String account) {
		Request request = new Request();
		request.setUrl(String.format(WX_ACCOUNT_CRAWL_URL, account));
		request.putExtra("wx_account", account);
		Spider.create(new WxAccountPageProcessor())
			.addRequest(request)
			//.addUrl(url)
			.thread(5)
			.run();
	}
	
	@Test
	public void test() {
		firstWebMagic("miss_rd");
	}
	
	@Test
	public void test2() {
		StringBuilder sb = new StringBuilder();
		sb.append("<body>");
		sb.append("<iframe class=\"video_iframe\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\" data-vidtype=\"-1\" data-ratio=\"1.7647058823529411\" data-w=\"480\" scrolling=\"no\" data-src=\"https://v.qq.com/iframe/preview.html?vid=w05435bn6tx&amp;width=500&amp;height=375&amp;auto=0\"></iframe>");
		sb.append("</body>");
		System.out.println(sb.toString());
		Page page = new Page();
		Html html = new Html(getBody());
		page.setHtml(html);
		String src = page.getHtml().xpath("//iframe/@data-src").get();
		System.out.println(src);
		String src2 = page.getHtml().xpath("*[@id=\"js_content\"]/h1/iframe/@data-src").get();
		System.out.println(src2);
		
	}
	
	public String getBody() {
		StringBuffer sb  = new StringBuffer();
		try {
			FileReader fr = new FileReader("D:\\Mtime\\workspace\\test\\src\\com\\mtime\\webmagic\\body.txt");
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
}
