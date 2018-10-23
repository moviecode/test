package com.itaiti.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

public class HttpTest {
	
	/**
	 * HTTP 请求
	 * 
	 * @param snedUrl
	 * @param encoded
	 * @return
	 */
	public String httpSend(String snedUrl, String encoded, String method) {
		String urlPath = snedUrl;
		StringBuffer sbf = new StringBuffer("");
		BufferedReader reader = null;
		HttpURLConnection uc = null;

		try {
			URL url = new URL(urlPath);
			uc = (HttpURLConnection) url.openConnection();
			uc.setConnectTimeout(30000);
			uc.setReadTimeout(30000);
			uc.setRequestMethod(method);//"GET"
			uc.setDoOutput(true);
			uc.setDoInput(true);
			uc.connect();
			reader = new BufferedReader(new InputStreamReader(uc.getInputStream())); // 读取服务器响应信息
			String line;
			while ((line = reader.readLine()) != null) {
				sbf.append(line);
			}
			reader.close();
			uc.disconnect();
			return sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	@Test
	public void postTest() {
		System.out.println(111);
		String url = "https://api.weibo.com/oauth2/access_token?client_id=166678827&client_secret=1f53004709ac0376fd3e0aae952946a2&grant_type=authorization_code&redirect_uri=http://passport.mtime.com/unitelogin/notify/1/&code=123456";
		String result = httpSend(url, "", "POST");
		PostMethod pm = new PostMethod();
	}
}
