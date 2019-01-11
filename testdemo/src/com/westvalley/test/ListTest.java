package com.westvalley.test;

import java.util.ArrayList;
import java.util.List;

import weaver.general.TimeUtil;

public class ListTest {
	
	public static void main(String[] args) {
	/*	List<String> list=new ArrayList<String>();
		//List<String> list=null;
		for (String string : list) {
			System.out.println(string);
		}*/
		
		String time="2018-09-30 10:36:14";
		String today = TimeUtil.getToday();
		/*String substring = time.substring(0, 10);
		System.out.println(substring);*/
		
		String datede = TimeUtil.dateAdd(today,-3)+" 23:59:59";
		String dateAdd = TimeUtil.dateAdd(today,3);
		System.out.println(today);
		System.out.println(dateAdd);
		System.out.println(datede);
	}

}
