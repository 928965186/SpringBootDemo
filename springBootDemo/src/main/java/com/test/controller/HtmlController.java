package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.test.entity.User;
import com.test.service.IUserService;

/**
 * 访问html页面
 * 
 * @author john
 *
 */
@Controller // 必须为controller
public class HtmlController {
	
	@Autowired
	private IUserService service;

	// 访问url
	@RequestMapping("test")
	public String test() {
		// 对应的html名称
		return "test";
	}

	// 访问url
	@RequestMapping("index")
	public String index() {
		// 对应的html名称
		return "index";
	}

	/*// 访问url
	@RequestMapping("index2")
	public String index2() {
		// 对应的html名称
		return "index2";
	}*/
	
	// 访问url
	@RequestMapping("login")
	public String login() {
		// 对应的html名称
		return "login";
	}

	// 访问url
	@RequestMapping("blog")
	public String blog() {
		// 对应的html名称
		return "blog";
	}

	// 访问url
	@RequestMapping("about_us")
	public String about() {
		// 对应的html名称
		return "about_us";
	}

	// 访问url
	@RequestMapping("contacts")
	public String contacts() {
		// 对应的html名称
		return "contacts";
	}

	// 访问url
	@RequestMapping("gallery")
	public String gallery() {
		// 对应的html名称
		return "gallery";
	}

	// 访问url
	@RequestMapping("layui")
	public String layui() {
		// 对应的html名称
		return "layui";
	}
	
	// 访问url
		@RequestMapping("uploadTest")
		public String uploadTest() {
			// 对应的html名称
			return "uploadTest";
		}
	
	/**
     * 主页
     *model 返回需要返回的数据
     *前台用th取出
     *并将数据展示在页面上
     * @param model
     * @return
     */
	@RequestMapping( "/index2")
    public String index(Model model) {        
        List<User> list=service.selectAll();
        model.addAttribute("user", list);
        return "index2";
    }
	
	// 访问url
		@RequestMapping(value="pagetest")
		public String page() {		
			return "pagetest";
		}
		
		@PostMapping(value="/ajaxtest",produces="application/json; charset=utf-8")
		@ResponseBody
	    public Object index(Model model,int page) {
			List<User> list=service.selectAll();
	        model.addAttribute("user", list);
	      JSONObject json=new JSONObject();

	      json.put("list", list);
			json.put("msg",page);
		
	        return json;
	       
	    }
	
}
