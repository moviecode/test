package com.itaiti.wx;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import goja.QRCode;

public class QrCodeTest {
	
	@Test
	public void decoderQrCodeTest(){
		String imgPath = "D:/134023.34374055.jpg";//"D:/385287581786449147.jpg";//"D:/wx.jpg";
		File qrcodeFile = new File(imgPath);
		//QRCode.from(qrcodeImage)
		String decoderContent = QRCode.from(qrcodeFile);
		System.out.println("������ά�룺"+decoderContent);
	}
	
	//http://weixin.qq.com/r/VmEHH97EjoPxrZp89zRE
	@Test
	public void decoderQrCodeTest2() throws IOException{
		byte[] bytes = getContent("D:/385287581786449147.jpg");
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);    //��b��Ϊ��������
		BufferedImage qrcodeImage = ImageIO.read(in);     //��in��Ϊ����������ȡͼƬ����image�У�������in����ΪByteArrayInputStream();
		String decoderContent = QRCode.from(qrcodeImage);
		System.out.println("������ά�룺"+decoderContent);
	}
	
	
	public byte[] getContent(String filePath) throws IOException {  
        File file = new File(filePath);  
        long fileSize = file.length();  
        if (fileSize > Integer.MAX_VALUE) {  
            System.out.println("file too big...");  
            return null;  
        }  
        FileInputStream fi = new FileInputStream(file);  
        byte[] buffer = new byte[(int) fileSize];  
        int offset = 0;  
        int numRead = 0;  
        while (offset < buffer.length  
        && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {  
            offset += numRead;  
        }  
        // ȷ���������ݾ�����ȡ  
        if (offset != buffer.length) {  
        throw new IOException("Could not completely read file "  + file.getName());  
        }  
        fi.close();  
        return buffer;  
    }
}
