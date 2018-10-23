package com.itaiti.http;

import org.junit.Test;


public class HttpClientTest {
	
	public HttpClient client = new HttpClient();
	
	@Test
	public void postTest() throws WeiboException {
		String url = "https://api.weibo.com/oauth2/access_token";
		PostParameter[] params =
		new PostParameter[] {
				new PostParameter("client_id", "166678827"),
				new PostParameter("client_secret", "1f53004709ac0376fd3e0aae952946a2"),
				new PostParameter("grant_type", "authorization_code"),
				new PostParameter("code", "11112222"),
				new PostParameter("redirect_uri","http://passport.mtime.com/unitelogin/notify/1/") 
		};
		Response response = client.post(url, params, false);
		System.out.println(response.getResponseAsString());
	}
}
