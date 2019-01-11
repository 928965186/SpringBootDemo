package com.test.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.test.entity.User;
import com.test.service.IUserService;



@RestController
public class UserController {
	@Autowired
	private IUserService service;
	
    @RequestMapping(value="/first.do",produces = "application/json; charset=utf-8")
    public Object first()  {
    	
    	/*JSONObject json=new JSONObject();
    	json.put("1", "1");
    	json.put("2", "2");
    	JSONArray json2=new JSONArray();
    	json2.add("111111");*/
    	
    	//User user=new User("1", "张三", "3333333");
    	User user = service.select("小明");
    	
        return user;
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

	@RequestMapping("/second.do")
    public String second() {
        return "test";
    }
	
	/**
	 * 接收前台ajax传来的值
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login.do",produces="application/json; charset=utf-8")
    public Object login(String username, String password,HttpServletRequest request) {
		JSONObject json=new JSONObject();
		json.put("msg","1");
		User user=new User("1", "1", "1");
		System.out.println(username);
		System.out.println(password);
		request.setAttribute("user", user);
        return json;
    }
	
	// 访问url
			@RequestMapping(value="pagetest.do",produces="application/json; charset=utf-8")
			public String page(String page) {
				System.out.println("page-----------------------------------"+page);
				
				return "pagetest";
			}
}
