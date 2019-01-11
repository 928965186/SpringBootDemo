package com.test.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.XML;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.system.SystemUtil;

public class test1 {
	public static void main(String[] args) throws IOException {
	/*	ExcelReader reader = ExcelUtil.getReader("C:\\Users\\john\\Desktop\\a.xls");
	List<Map<String, Object>> readAll = reader.readAll();
	for (Map<String, Object> map : readAll) {
		System.out.println(map);
	}*/
		
		
		/*	System.out.println(SystemUtil.getHostInfo());
		System.out.println(SystemUtil.getOsInfo());
		System.out.println(SystemUtil.getUserInfo().getCountry());
		System.out.println(SystemUtil.getHostInfo().getAddress());*/
		
		
		/*String s="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<bookstore>\r\n" + 
				"    <book id=\"1\">\r\n" + 
				"        <name>冰与火之歌</name>\r\n" + 
				"        <author>乔治马丁</author>\r\n" + 
				"        <year>2014</year>\r\n" + 
				"        <price>89</price>\r\n" + 
				"    </book>\r\n" + 
				"    <book id=\"2\">\r\n" + 
				"        <name>安徒生童话</name>\r\n" + 
				"        <year>2004</year>\r\n" + 
				"        <price>77</price>\r\n" + 
				"        <language>English</language>\r\n" + 
				"    </book>    \r\n" + 
				"</bookstore>";
	
		JSONObject xmlToJson = JSONUtil.xmlToJson(s);
		Object object = xmlToJson.get("bookstore");
		System.out.println(object);
		*/
		
	String string = HttpUtil.get("https://www.baidu.com/");
	System.out.println(string);
		
	}
}
