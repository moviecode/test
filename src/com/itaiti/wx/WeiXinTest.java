package com.itaiti.wx;

import org.junit.Test;

import com.tencent.mm.opensdk.openapi.IWXAPI;

public class WeiXinTest{
	private static final String WECHAT_APP_ID = "������Ӧ�õõ���APPID";
	// IWXAPI�ǵ�����app��΢��ͨ�ŵ�openapi�ӿ�
    private IWXAPI iwxapi;
	
	@Test
	public void test(){
		System.out.println(111111);
		//WXAPIFactory.createWXAPI(null, WECHAT_APP_ID, true);
		//iwxapi = WXAPIFactory.createWXAPI(this,WECHAT_APP_ID, true);
        iwxapi.registerApp(WECHAT_APP_ID);
	}

}	
