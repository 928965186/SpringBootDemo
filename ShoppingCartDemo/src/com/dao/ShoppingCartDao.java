package com.dao;

import java.util.List;

import com.entity.Shopping;
import com.entity.ShoppingCart;

public interface ShoppingCartDao {
	
	int save(Shopping shopping);
	
	int delete(long id);
	
	List<ShoppingCart> select(String ShoppingName);
	
	List<ShoppingCart> select();
}
