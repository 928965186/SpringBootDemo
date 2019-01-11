package com.dao.test;


import java.util.List;

import com.dao.ShoppingCartDao;
import com.dao.impl.ShoppingCartDaoImpl;
import com.entity.Shopping;
import com.entity.ShoppingCart;

public class ShoppingCartDaoImplTest {

	
	public static void main(String[] args) {
		Shopping shopping=new Shopping(null);
		
		shopping.setShoppingid(1l);
		shopping.setShoppingname("1");
		
		ShoppingCartDao dao=new ShoppingCartDaoImpl();
		//dao.save(shopping);
		List<ShoppingCart> select = dao.select("µçÊÓ»ú");
		System.out.println(select);
//		int i = dao.delete(1);
//		System.out.println(i);
		
	}
}
