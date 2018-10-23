package com.itaiti.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class HttpClientTest {
	
	@Test
	public void getAccessToken() {
        //String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&appid=wx839739a08ff78016&secret=d26bf53be1cae30a469920407c78f329&code=013QV0oW1StjuV0yiBmW1odZnW1QV0op";
        URI uri = URI.create(url);
        //ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();  
        //Protocol.registerProtocol("https", new Protocol("https", fcty, 443));  
        @SuppressWarnings({ "deprecation", "resource" })
		HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(uri);
        HttpResponse response;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                    sb.append(temp);
                }
                System.out.println(sb.toString().trim());
                JSONObject object = JSONObject.parseObject(sb.toString().trim());
                String accessToken = object.getString("access_token");
                String openID = object.getString("openid");
                String refreshToken = object.getString("refresh_token");
                Long expires_in = object.getLong("expires_in");
                //return accessToken;
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	@Test
	public void getSinaWeiboAccessToken() {
		//String url = "https://api.weibo.com/oauth2/access_token?client_id=166678827&client_secret=1f53004709ac0376fd3e0aae952946a2&grant_type=authorization_code&redirect_uri=http://passport.mtime.com/unitelogin/notify/1/&code=123456";
        String url = "https://api.weibo.com/oauth2/access_token";
		URI uri = URI.create(url);
        //ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();  
        //Protocol.registerProtocol("https", new Protocol("https", fcty, 443));  
        @SuppressWarnings({ "deprecation", "resource" })
		HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(uri);
        //HttpGet get = new HttpGet(uri);
        HttpResponse response;
        try {
            response = client.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getEntity().getContentType());
            System.out.println(response.getEntity().getContent());
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                    sb.append(temp);
                }
                System.out.println(sb.toString().trim());
                JSONObject object = JSONObject.parseObject(sb.toString().trim());
                String accessToken = object.getString("access_token");
                String openID = object.getString("openid");
                String refreshToken = object.getString("refresh_token");
                Long expires_in = object.getLong("expires_in");
                //return accessToken;
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
