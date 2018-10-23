package com.itaiti.wx;

import org.junit.Test;

import com.tencent.mm.opensdk.openapi.IWXAPI;

public class WeiXinTest{
	private static final String WECHAT_APP_ID = "你申请应用得到的APPID";
	// IWXAPI是第三方app和微信通信的openapi接口
    private IWXAPI iwxapi;
	
	@Test
	public void test(){
		System.out.println(111111);
		//WXAPIFactory.createWXAPI(null, WECHAT_APP_ID, true);
		//iwxapi = WXAPIFactory.createWXAPI(this,WECHAT_APP_ID, true);
        iwxapi.registerApp(WECHAT_APP_ID);
	}

}	
