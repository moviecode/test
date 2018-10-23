package com.itaiti.test;

import java.security.MessageDigest;

/**
 * MD5加密解密工具类
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017年2月23日 下午6:49:29
 * @version V1.0
 */
public class MD5Util {
	
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString
                    .getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }
    /**
     * 校验
     * @param newPassword
     * @param oldPassport
     * @return
     */
    public static boolean isValidate(String newPassword,String oldPassport){
        
        boolean status = false;
        
        if(MD5Util.MD5Encode(newPassword).equals(oldPassport)){
            status = true;
        }else{
            status = false;
        }
        
        return status;
    }
    
    public static void main(String[] args) {
        
        System.out.println(MD5Encode("111111"));
        //96E79218965EB72C92A549DD5A330112
        //96e79218965eb72c92a549dd5a330112
        boolean b = MD5Util.isValidate("123", "202cb962ac59075b964b07152d234b70");
        System.out.println(b);
        
    }
}
