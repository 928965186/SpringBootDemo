package com.westvalley.fk.test;

import java.util.ArrayList;
import java.util.List;

public class test {
	
	public static void main(String[] args) {
		List<Object> list=new ArrayList<Object>();
		String id="222";
		String name="333";
		list.add(id);
		list.add(name);
		for (Object object : list) {
			System.out.println(list.get(1));
			
		}
		//System.out.println(list.get(1));
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			
		}
	}
}
