package com.test.service;

import java.util.List;

import com.test.entity.User;

public interface IUserService {
	
	User select(String username);
	
	List<User> selectAll();
	
	int insert(String username,String password);
	
	int update(String id,String password);
	
	int delete(String id);
	

}
