package com.dao;

import java.util.List;

import com.entity.Shopping;

public interface ShoppingDao {
	
	int save(Shopping shopping);
	
	List<Shopping> select();
	
	List<Shopping> select(long id);
}
