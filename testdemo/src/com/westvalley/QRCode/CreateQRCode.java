package com.westvalley.QRCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode {
	
	public static void main(String[] args) throws IOException {
		 Qrcode qrcode = new Qrcode();	   
		 qrcode.setQrcodeErrorCorrect('M');//����ȼ�����ΪL��M��H�����ȼ���	   
		 qrcode.setQrcodeEncodeMode('B');//N�������֣�A����a-Z��B���������ַ�	   
		 qrcode.setQrcodeVersion(20);//�汾	   
		 //���ɶ�ά����Ҫ�洢����Ϣ	   
		 String qrData = "ɨ�Ҹ��";	   
		 //����һ�¶�ά�������	   
		 int width = 67+12*(20-1);	   
		 int height = 67+12*(20-1);	   
		 BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);	   //��ͼ	   
		 Graphics2D gs = bufferedImage.createGraphics();	   
		 gs.setBackground(Color.WHITE);	   
		 gs.setColor(Color.BLACK);	   
		 gs.clearRect(0, 0, width, height);//����»�������	   	    
		 //������ƫ����,�������ƫ��������ʱ�ᵼ�³���	   
		 int pixoff = 2;	   	   
		 byte[] d = qrData.getBytes("utf-8");	   
		 if(d.length > 0 && d.length <120){
			 boolean[][] s = qrcode.calQrcode(d);		   
		 for(int i=0;i<s.length;i++){			   
			 for(int j=0;j<s.length;j++){				   
				 if(s[j][i]){					   
					 gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);				   
					 }			   
				 }		   
			 }	   
		 }	   
		 gs.dispose();	   
		 bufferedImage.flush();	   
		 ImageIO.write(bufferedImage, "png", new File("F:/qrcode.png")); 
		 }
	
	}

