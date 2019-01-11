package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.dao.UserMapper;
import com.test.entity.User;
import com.test.service.IUserService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IUserService service;
	
	@Test
	public void contextLoads() {
		
		int insert = userMapper.insert("longer", "1");
		System.out.println("增加了"+insert+"行");
		
		//int update1 = userMapper.update("22222", "55");
		//int i=7/0;
		
		int update = service.update("55", "5");
		
		System.out.println("修改了"+update+"行");
		
		//userMapper.delete("53");
		
		/*User select = service.select("1");
		System.out.println(select);*/
		
		/*List<User> selectAll = userMapper.selectAll();
		for (User user : selectAll) {
			System.out.println(user);
		}*/
		
	}

}
