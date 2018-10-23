package com.itaiti.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class FileToByteArray {
	
	public static byte[] readFileToByteArray(String path) {
		byte[] buffer = null;
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			byte[] b = new byte[1000];
			int len = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			while((len = fis.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return buffer;
	}
	
	public static byte[] readFileToByteArray2(String path) {
		byte[] buffer = null;
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			byte[] b = new byte[1000];
			int len = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			while((len = fis.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return buffer;
	}
	
	/**
     * 将二进制转换成文件保存
     * @param instreams 二进制流
     * @param imgPath 图片的保存路径
     * @param imgName 图片的名称
     * @return 
     *      1：保存正常
     *      0：保存失败
     */
    public static int saveToImgByInputStream(InputStream instreams,String imgPath,String imgName){
        int stateInt = 1;
        if(instreams != null){
            try {
                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file);
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();                
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }
    
    public static int saveToImgByInputStream(byte[] bytes,String imgPath,String imgName){
        int stateInt = 1;
        if(bytes != null && bytes.length > 0){
            try {
                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file);
                fos.write(bytes);
                fos.flush();
                fos.close();                
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }
    
    public static InputStream downloadVideo(String url) {
    	try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response = client.execute(new HttpGet(url));
            InputStream in = response.getEntity().getContent();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("download video fail.httpStatus=" + statusCode);
            }
            return in;
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
    }
	
	public static void main(String[] args) {
		// byte[] b = readFileToByteArray2("C:\\Users\\Mtime\\Desktop\\ronaldo.jpg");
		// System.out.println(b);
		//String url = "https://v.qq.com/iframe/preview.html?vid=w05435bn6tx&amp;width=500&amp;height=375&amp;auto=0";
		//String url = "http://gslb.miaopai.com/stream/9yEg2Z1SVuP29~~dsFhNHLgURp3o168kJhLMqA__.mp4";
			//?ssig=36ed7b4059168c20a6fb7a0404b449a2&time_stamp=1506324206556&cookie_id=&vend=1&os=3&partner=1&platform=2&cookie_id=&refer=miaopai&scid=9yEg2Z1SVuP29%7E%7EdsFhNHLgURp3o168kJhLMqA__
		String url = "http://ugcydzd.qq.com/flv/64/150/n0371apcnd7.mp4?sdtfrom=v1000&type=mp4&vkey=14F797E583F4039BAF9FEF7A4F2B3DF5FCC28F4898AD6FF31082A599F432218DF99A7B9451B6C7147F1D249B661196411F9044798D8DE6702F1111E2A07658502A9DBD638EC8D1213198BA5B2BF2EE2D6282A7F4C8C0067607CB9A4DF644F4B8FA9C600C343218FBE5F794CE9EB8A8F1477731172D66FA1F&level=0&platform=70202&br=60&fmt=hd&sp=0&guid=659DCBBAE578FB7B4F72E1295E788A3A1D04B701";
		InputStream in = downloadVideo(url);
		saveToImgByInputStream(in, "D:\\", "video3.mp4");
		//byte[] bytes = readFileToByteArray("C:\\Users\\Mtime\\Desktop\\1111.mp4");
		//saveToImgByInputStream(bytes, "D:\\", "video.mp4");
	}
}
