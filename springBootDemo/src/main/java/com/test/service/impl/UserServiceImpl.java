package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.UserMapper;
import com.test.entity.User;
import com.test.service.IUserService;

@Service//定义为service层
@Transactional//开启事务
public class UserServiceImpl implements IUserService{
	//注入dao层
	@Autowired
	private UserMapper userDao;
	
	@Override
	public User select(String username) {
		User user = userDao.select(username);
		return user;
	}

	@Override
	public int insert(String username, String password) {
		int i = userDao.insert(username, password);
		return i;
	}

	@Override
	public int update(String id, String password) {
		int i = userDao.update(id, password);
		return i;
	}

	@Override
	public int delete(String id) {
		int i = userDao.delete(id);
		return i;
	}

	@Override
	public List<User> selectAll() {
		
		return userDao.selectAll();
	}

}
