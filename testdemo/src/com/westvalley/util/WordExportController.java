package com.westvalley.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;
 
public class WordExportController {
 
	public static void main(String[] args) throws FileNotFoundException, IOException {
		WordExportController test = new WordExportController();
		test.createWord();
		//String s="123,545";
		//System.out.println(s.contains("7"));
	}
 
	private Configuration configuration = null;
 
	public WordExportController() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
	}
 
	public void createWord( ) throws FileNotFoundException, IOException {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<String> list=new ArrayList<String>();
		/*list.add("link1<w:p></w:p>");
		list.add("link2<w:p></w:p>");		
		list.add("link3<w:p></w:p>");*/
		
		String l="1,2,3";
		String[] split = l.split(",");
		for (String string : split) {
			list.add(string+"<w:p></w:p>");
		}
		
		dataMap.put("title", "titletest");
		dataMap.put("No", "Notest");
		dataMap.put("data", "datatest");
		dataMap.put("links", list);
		 
		List<Object> imagelist=new ArrayList<Object>();
	
		
		imagelist.add("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");
		
		imagelist.add("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");
		
		dataMap.put("images", imagelist);
    	
		try {
			configuration.setClassForTemplateLoading(this.getClass(), "/com/westvalley/template"); // FTL文件所存在的位置
			Template template = configuration.getTemplate("temple.ftl");
			File outFile = new File("C:/Users/john/Desktop/测试.doc");
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
			template.process(dataMap, out);
			out.close(); 
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public String getImageStr(String path) {
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
		//return data.toString();
	}
	
	public byte[] getImage(String path) {
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
		//return encoder.encode(data);
		return data;
	}
	
	public  boolean base64ToFile(String base64Str, String filePath) {
        if (base64Str == null && filePath == null) {
            return false;
        }
        try {
            Files.write(Paths.get(filePath), Base64.getDecoder().decode(base64Str.replace("\r\n", "")), StandardOpenOption.CREATE);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


	
}