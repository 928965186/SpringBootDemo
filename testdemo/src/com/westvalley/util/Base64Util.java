package com.westvalley.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

import sun.misc.BASE64Encoder;


public class Base64Util {
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
	     * ��ԭʼ���ݱ���Ϊbase64���� 
	     */  
	    static public String encode(byte[] data) {  
	        char[] out = new char[((data.length + 2) / 3) * 4];  
	        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {  
	            boolean quad = false;  
	            boolean trip = false;  
	            int val = (0xFF & (int) data[i]);  
	            val <<= 8;  
	            if ((i + 1) < data.length) {  
	                val |= (0xFF & (int) data[i + 1]);  
	                trip = true;  
	            }  
	            val <<= 8;  
	            if ((i + 2) < data.length) {  
	                val |= (0xFF & (int) data[i + 2]);  
	                quad = true;  
	            }  
	            out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];  
	            val >>= 6;  
	            out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];  
	            val >>= 6;  
	            out[index + 1] = alphabet[val & 0x3F];  
	            val >>= 6;  
	            out[index + 0] = alphabet[val & 0x3F];  
	        }  
	          
	        return new String(out);  
	    }  

	    /** 
	     * ��base64��������ݽ����ԭʼ���� 
	     */  
	    static public byte[] decode(char[] data) {  
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
	
	/**
    *
    * @param path
    * @return String
    * @description ���ļ�תbase64�ַ���
    * @date 2018��3��20��
    * @author changyl
    * Fileת�ɱ����BASE64
    */

   public static  String fileToBase64(String path) {
       String base64 = null;
       InputStream in = null;
       try {
           File file = new File(path);
           in = new FileInputStream(file);
           byte[] bytes=new byte[(int)file.length()];
           in.read(bytes);
           base64 = Base64.getEncoder().encodeToString(bytes);
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (in != null) {
               try {
                   in.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
       return base64;
   }
   
 //BASE64�����File�ļ�
   public static void base64ToFile(String destPath,String base64, String fileName) {
       File file = null;
       //�����ļ�Ŀ¼
       String filePath=destPath;
       File  dir=new File(filePath);
       if (!dir.exists() && !dir.isDirectory()) {
           dir.mkdirs();
       }
       BufferedOutputStream bos = null;
       java.io.FileOutputStream fos = null;
       try {
           byte[] bytes = Base64.getDecoder().decode(base64);
          
           file=new File(filePath+"/"+fileName);
           fos = new java.io.FileOutputStream(file);
           bos = new BufferedOutputStream(fos);
           bos.write(bytes);
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (bos != null) {
               try {
                   bos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           if (fos != null) {
               try {
                   fos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
   }
   
   public static byte[] getStrBystr(String path) {
		String imgFile = path;
		InputStream in = null;
		byte[] data = null;
		InputStreamReader isr=null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		//return encoder.encode(data);
		return data;
	}
   
   public static char[] getStrBychar(String path) {
		String imgFile = path;
		InputStream in = null;
		InputStreamReader isr=null;
		byte[] data = null;
		char[] cs=null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			cs=char[].class.cast(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		//return encoder.encode(data);
			return cs;
	}
   
   
   
 
    /** 
     * ��base64��������ݽ����ԭʼ���� 
     */  
     static public byte[] decode(byte[] data) {  
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
    /**
     * ���ֽ�Ϊ��λ��ȡ�ļ��������ڶ��������ļ�����ͼƬ��������Ӱ����ļ���
     */
    public static byte[] readFileByBytes(String fileName) {
        File file = new File(fileName);
        byte[] tempbytes=null;
        InputStream in = null;
        try {
            System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
            // һ�ζ�����ֽ�
             tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            
            // �������ֽڵ��ֽ������У�bytereadΪһ�ζ�����ֽ���
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
		return tempbytes;
    }

    /**
     * ���ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
     */
    public static  char[] readFileByChars(String fileName) {
        File file = new File(fileName);
        char[] tempchars =null;
        Reader reader = null;
    
        try {
           // System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
            // һ�ζ�����ַ�
            tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // �������ַ����ַ������У�charreadΪһ�ζ�ȡ�ַ���
            while ((charread = reader.read(tempchars)) != -1) {
                // ͬ�����ε�\r����ʾ
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    //System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            //System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return tempchars;
    }
	
    
   
    
        public static String encode(String s) {
            if (s == null)
                return null;
            String res = "";
            try {
                res = new sun.misc.BASE64Encoder().encode(s.getBytes("GBK"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return res;
        }

        /**
         * �� BASE64 ������ַ��� s ���н���
         *
         * @return String
         * @author lifq
         * @date 2015-3-4 ����09:24:26
         */
        public static String decode(String s) {
            if (s == null)
                return null;
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            try {
                byte[] b = decoder.decodeBuffer(s);
                return new String(b,"GBK");
            } catch (Exception e) {
                return null;
            }
    
        }
        
        public static char[] getChars(byte[] bytes) {
            Charset cs = Charset.forName("UTF-8");
            ByteBuffer bb = ByteBuffer.allocate(bytes.length);
            bb.put(bytes);
            bb.flip();
            CharBuffer cb = cs.decode(bb);
            return cb.array();
        }
        
        /**
         * �ֽ�����ת��16���Ʊ�ʾ��ʽ���ַ���
         * 
         * @param byteArray
         *            ��Ҫת�����ֽ�����
         * @return 16���Ʊ�ʾ��ʽ���ַ���
         **/
        public static String toHexString(byte[] byteArray) {
         if (byteArray == null || byteArray.length < 1)
          throw new IllegalArgumentException("this byteArray must not be null or empty");
        
         final StringBuilder hexString = new StringBuilder();
         for (int i = 0; i < byteArray.length; i++) {
          if ((byteArray[i] & 0xff) < 0x10)//0~Fǰ�治��
           hexString.append("0");
          hexString.append(Integer.toHexString(0xFF & byteArray[i]));
         }
         return hexString.toString().toLowerCase();
        }
        
}
