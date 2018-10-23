package com.itaiti.verifycode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import mtime.lark.util.cache.Cache;

public class VerifyCode {
	
	private static int MAX_WIDTH = 120;
	private static int MAX_HEIGHT = 60;
	private static int INTERVAL = 3 * 60 * 1000;//in milliseconds
	private static int MAX_COUNT = 100000;
	private static String CODE_CACHE_KEY = "__ValidateCode_";
	private static int CODE_CACHE_MINUTE = 3;
	static char [] CODE = new char [] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'c', 'd', 'f', 'k', 'm', 'n', 'p', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'C', 'D', 'F', 'G', 'H', 'K', 'M', 'N', 'P', 'Q', 'R', 'U', 'W', 'X', 'Y', 'Z' };
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
	
	public VerifyCode() {
       //StartClearExpiredCodeTimer();
    }
	
	public String generate() {
		String obfuscateCode = newShortID();
		String realCode = getRealCode(4);
        setCodeItem(obfuscateCode, new CodeItem(realCode));
        return obfuscateCode;
    }
	
	/**
	 * 生成指定长度的验证码 数字+字母组合
	 * @param length
	 * @return
	 */
	public static String getRealCode( int length){
        StringBuilder builder = new StringBuilder();
  
        for ( int i = 0, count = length; i < count; ++i ) {
            builder.append(CODE[new Random().nextInt(CODE.length)]);
        }
        return builder.toString();
    }
	
	/**
	 * 生成18位id
	 * @return
	 */
	public static String newShortID() {
		return sdf.format(new Date()) + (new Random().nextInt(1000)%900 +100);
    }
	
	public static CodeItem getCodeItem(String obfuscateCode ) {
		//String cacheKey = String.format ( CODE_CACHE_KEY, obfuscateCode );
		// CodeItem code = RedisCachingService.Current.GetForDataCache ( cacheKey ) as CodeItem;
		return Cache.get(CodeItem.class, CODE_CACHE_KEY, obfuscateCode);
	}

	public static void setCodeItem(String obfuscateCode, CodeItem code ) {
		// String cacheKey = String.format (CODE_CACHE_KEY , obfuscateCode );
		// RedisCachingService.Current.AddForDataCache ( cacheKey, code, CODE_CACHE_MINUTE );
		Cache.set(code, CODE_CACHE_KEY, obfuscateCode);
	}

	public static void removeCodeItem(String obfuscateCode ) {
		// String cacheKey = String.format ( CODE_CACHE_KEY, obfuscateCode );
		// RedisCachingService.Current.Remove( cacheKey );
		Cache.remove(CODE_CACHE_KEY, obfuscateCode);
	} 
	
	public static void main(String[] args) {
		for(int i=0; i<50; i++){
			System.out.println(newShortID());
		}
		// System.out.println("getRealCode:"+getRealCode(4));
		//180123185601427609
		//180124102122184968
	}
}
