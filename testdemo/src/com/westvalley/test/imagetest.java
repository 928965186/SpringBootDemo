package com.westvalley.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class imagetest {

	private static String URL = "http://222.84.136.247:808/weaver/weaver.file.FileDownload?fileid=478&download=1";

	
	public static void main(String[] args) throws Exception {
		//readLineFile();
		 
		download(URL,"8.txt", "C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\mbi2");
	}
	
	public static void readLineFile() throws Exception {

		List<String>list= new ArrayList<>();


		FileInputStream in = new FileInputStream("C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\33.txt");
		InputStreamReader inReader= new InputStreamReader(in,"UTF-8");
		BufferedReader bufReader= new BufferedReader(inReader);
		String line= null;
		while ((line = bufReader.readLine()) != null) {
				list.add(line);
			}

			bufReader.close();

			inReader.close();

			in.close();

			for (String string : list) {

				String[]array= string.split(",");


				String encode= URLEncoder.encode(array[1], "UTF-8");
				download(URL + encode, array[0] + ".jpg", "C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\mbi2");
			}
}
	
	private static synchronized void download(String urlString, String filename, String savePath ) throws Exception {
		 URL url= new URL(urlString); // 打开连接
		 HttpURLConnection con= (HttpURLConnection) url.openConnection();// 设置请求超时为5s
		 con.setConnectTimeout(10 *1000); // 输入流
		 InputStream is = con.getInputStream();// 1K的数据缓冲
		 byte[] bs = new byte[1024];// 读取到的数据长度
		 int len;// 输出的文件流
		 File sf = new File(savePath);
		 if (!sf.exists()) {
			 sf.mkdirs();
		 }
		 OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);// 开始读取
		 while ((len = is.read(bs)) != -1) {
			 os.write(bs, 0, len);
		 }// 完毕，关闭所有链接

		 os.close();
		 is.close();
	

	}

}