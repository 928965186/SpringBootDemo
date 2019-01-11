package com.westvalley.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


import sun.misc.BASE64Encoder;


public class test1 {
	public static void main(String[] args) throws IOException {
		String path="C:\\Users\\john\\Desktop\\1.png";
		String imageStr = getImageStr(path);
		System.out.println(imageStr);
		
		byte[] decode = decode(imageStr.toCharArray());
		File f=new File("C:\\\\Users\\\\john\\\\Desktop\\\\2.jpg");
		OutputStream os=null;
		os=new FileOutputStream(f);
		os.write(decode);
		
	}
	
	/**
	 * ��ͼƬתΪbase64
	 * @param path ͼƬ·��
	 * @return
	 */
		public static String getImageStr(String path) {
			String imgFile = path;
			InputStream in = null;
			byte[] data = null;
			try {
				in = new FileInputStream(imgFile);
				data = new byte[in.available()];
				in.read(data);
				in.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);
		}
		
		
		static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();  
	    static private byte[] codes = new byte[256];  
	    static {  
	        for (int i = 0; i < 256; i++)  
	            codes[i] = -1;  
	        for (int i = 'A'; i <= 'Z'; i++)  
	            codes[i] = (byte) (i - 'A');  
	        for (int i = 'a'; i <= 'z'; i++)  
	            codes[i] = (byte) (26 + i - 'a');  
	        for (int i = '0'; i <= '9'; i++)  
	            codes[i] = (byte) (52 + i - '0');  
	        codes['+'] = 62;  
	        codes['/'] = 63;  
	    }  
	      
	  

	    /** 
	     * ��base64��������ݽ����ԭʼ���� 
	     */  
	     public static  byte[] decode(char[] data) {  
	        int len = ((data.length + 3) / 4) * 3;  
	        if (data.length > 0 && data[data.length - 1] == '=')  
	            --len;  
	        if (data.length > 1 && data[data.length - 2] == '=')  
	            --len;  
	        byte[] out = new byte[len];  
	        int shift = 0;  
	        int accum = 0;  
	        int index = 0;  
	        for (int ix = 0; ix < data.length; ix++) {  
	            int value = codes[data[ix] & 0xFF];  
	            if (value >= 0) {  
	                accum <<= 6;  
	                shift += 6;  
	                accum |= value;  
	                if (shift >= 8) {  
	                    shift -= 8;  
	                    out[index++] = (byte) ((accum >> shift) & 0xff);  
	                }  
	            }  
	        }  
	     
	        return out;  
	    }  
}
