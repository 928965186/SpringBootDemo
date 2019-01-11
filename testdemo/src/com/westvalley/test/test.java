package com.westvalley.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.westvalley.util.Base64Util;
import com.westvalley.util.HttpUtil;
import com.westvalley.util.SymmetricEncoder;
import com.westvalley.util.WordExportController;

import sun.misc.BASE64Encoder;
import weaver.aes.AES;
import weaver.conn.BatchRecordSet;
import weaver.docs.docs.ImageFileIdUpdate;
import weaver.file.FileDownload;

import com.westvalley.util.AESCoder;
import com.westvalley.util.AESFileInfo;
import com.westvalley.util.AESUtil;
import com.westvalley.util.Base64;
import weaver.soa.workflow.FileProcessor;

public class test {
	
	@SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception {
		/*try {
			Map<String, String> readfileAll = FileUtil.readfileAll("C:\\Users\\john\\Desktop\\新建文件夹\\1925752035");
			System.out.println(readfileAll.values());
			System.out.println(readfileAll.keySet());
			Collection<String> values = readfileAll.values();
			
			//System.out.println(readfileAll);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*File file=new File("C:\\Users\\john\\Desktop\\新建文件夹\\df34983c-e880-4cc8-9858-957a88043bd1\\df34983c-e880-4cc8-9858-957a88043bd1");
		InputStream in= null;
		try {
			in=new FileInputStream(file);
			byte b[] = new byte[1024] ;
			int len = in.read(b);
			in.close();
			System.out.println("内容为：" + new String(b,0,len)) ;
			BASE64Encoder encoder = new BASE64Encoder();
			//System.out.println(encoder.encode(b)); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileDownload download=new FileDownload();*/
		
		/*String url="http://222.84.136.247:808/weaver/weaver.file.FileDownload?fileid=478&download=1";
		String doGet = HttpUtil.doGet(url);
		String doPost = HttpUtil.doPost(url, "");
		System.out.println(doPost);*/
		
		/*FileProcessor p=new FileProcessor();
		File file=new File("");
		String base64 = FileProcessor.fileToBase64(file);
		System.out.println(base64);*/
		/*byte[] str=null;
		 str = Base64Util.getStrBystr("C:\\Users\\john\\Desktop\\新建文件夹\\07b664b4-821c-4868-a3b1-d441714de518\\07b664b4-821c-4868-a3b1-d441714de518");
		//System.out.println(str);
		byte[] decode = Base64Util.decode(str);
	for (byte b : decode) {
		System.out.print(b);
		
	}
	BASE64Encoder encoder = new BASE64Encoder();
	 System.out.println(encoder.encode(decode));*/
	//Base64Util.base64ToFile("C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\", "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", "1.png");
	
		
		//Base64Util.readFileByBytes("C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\07b664b4-821c-4868-a3b1-d441714de518\\\\07b664b4-821c-4868-a3b1-d441714de518");
		//Base64Util.readFileByBytes("C:\\Users\\john\\Desktop\\3.png");
		
		//char[] data = Base64Util.readFileByChars("C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\07b664b4-821c-4868-a3b1-d441714de518\\\\07b664b4-821c-4868-a3b1-d441714de518");
		//char[] data = Base64Util.readFileByChars("C:\\Users\\john\\Desktop\\2.png");

		//byte[] data = Base64Util.readFileByBytes("C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\07b664b4-821c-4868-a3b1-d441714de518\\\\07b664b4-821c-4868-a3b1-d441714de518");
		
		/*byte[] data = Base64Util.readFileByBytes("C:\\Users\\john\\Desktop\\2.png");
		byte[] decode = Base64Util.decode(data);
		for (byte b : decode) {
			System.out.print(b);

		}
		
		BASE64Encoder encoder = new BASE64Encoder();
		 String encode = encoder.encode(decode);
		System.out.println(encode);*/

		
		
		/*String encode = Base64Util.encode("哈哈");
		System.out.println(encode);
		String decode = Base64Util.decode("uf65/g==");
		System.out.println(decode);*/
		
		
		WordExportController controller=new WordExportController();
		//String imageStr = controller.getImageStr("C:\\\\Users\\\\john\\\\Desktop\\\\3.jpg");
		//String imageStr = controller.getImageStr("C:\\\\\\\\Users\\\\\\\\john\\\\\\\\Desktop\\\\\\\\新建文件夹\\\\\\\\60317d53-0fe7-49c1-92d6-05e5ab64d118\\\\\\\\60317d53-0fe7-49c1-92d6-05e5ab64d118");

		//System.out.println(imageStr);
		
		byte[] image = controller.getImage("C:\\\\Users\\\\john\\\\Desktop\\\\3.jpg");
		//byte[] image = controller.getImage("C:\\Users\\john\\Desktop\\新建文件夹\\60317d53-0fe7-49c1-92d6-05e5ab64d118\\60317d53-0fe7-49c1-92d6-05e5ab64d118");
		//byte[] image = controller.getImage("C:\\Users\\john\\Desktop\\新建文件夹\\1.txt");
		//byte[] image = controller.getImage("C:\\Users\\john\\Desktop\\新建文件夹\\1394675283\\1394675283");
		//byte[] image = controller.getImage("C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\3");
		
		//byte[] image = controller.getImage("C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\b1d1e672-3038-4f22-a68d-466ba2325056\\\\b1d1e672-3038-4f22-a68d-466ba2325056");
		
		for (byte b : image) {
			//System.out.print(b);
		}
		
		//System.out.println(imageStr);
		//System.out.println(new String(image));
		//BASE64Encoder encoder = new BASE64Encoder();
		// String encode = encoder.encode(image);
		
		
		//System.out.println(imageStr);
	/*FileOutputStream fio=new FileOutputStream("C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\3.doc");
		
		
		fio.write(image);
		
		fio.close();*/
	
		//String encode = Base64Util.encode(image);
		//byte[] decode = Base64Util.decode(image);
		//byte[] decode =Base64Util.decode(Base64Util.getChars(image));
		
		//String decode = Base64Util.decode(Base64Util.toHexString(image));
		//String decode = (Base64Util.toHexString(image));
		//String decode = new String(image);
		/*for (byte b : decode) {
			System.out.print(b);
		}*/
		//System.out.println(decode);
		
		byte [] b=null;
		InputStream fis=null;
		fis=new FileInputStream("C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\60317d53-0fe7-49c1-92d6-05e5ab64d118\\\\60317d53-0fe7-49c1-92d6-05e5ab64d118");
		BufferedInputStream bis=new BufferedInputStream(fis);
		 bis.available();
		 b=new byte[bis.available()];
		 
		
		 
		 bis.read(b);
		 for (byte c : b) {
			System.out.print(c);
		}
		 fis.close();
		 bis.close();
		 
		  byte[] decrypt = AESUtil.decrypt(b, "GPZAPD2eXon1Z");
		  
		BASE64Encoder encoder = new BASE64Encoder();
		String encode = encoder.encode(decrypt);	
		//System.out.println(encode.replaceAll("\r\n", ""));
	}

}
