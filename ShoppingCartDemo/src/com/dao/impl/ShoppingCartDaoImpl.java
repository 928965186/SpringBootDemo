package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.ShoppingCartDao;
import com.entity.Shopping;
import com.entity.ShoppingCart;
import com.util.ConnectionFactory;

public class ShoppingCartDaoImpl implements ShoppingCartDao{

	@Override
	public int save(Shopping shopping) {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionFactory.getConnection();
		String sql="insert into ShoppingCart values(?,?)";
		PreparedStatement pstmt;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, shopping.getShoppingid());
			pstmt.setString(2, shopping.getShoppingname());
			i=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<ShoppingCart> select(String ShoppingName) {
		// TODO Auto-generated method stub
		List<ShoppingCart> list=new ArrayList<ShoppingCart>();
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from ShoppingCart where shoppingCartname like ? order by SHOPPINGCARTID asc";
		PreparedStatement pstmt;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+ShoppingName+"%");			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				ShoppingCart cart=new ShoppingCart();
				cart.setShoppingcartid(rs.getLong(1));
				cart.setShoppingcartname(rs.getString(2));
				list.add(cart);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<ShoppingCart> select() {
		// TODO Auto-generated method stub
		List<ShoppingCart> list=new ArrayList<ShoppingCart>();
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from ShoppingCart order by SHOPPINGCARTID asc";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				ShoppingCart cart=new ShoppingCart();
				cart.setShoppingcartid(rs.getLong(1));
				cart.setShoppingcartname(rs.getString(2));
				list.add(cart);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		
		int i=0;
		Connection conn = ConnectionFactory.getConnection();
		String sql="delete ShoppingCart where shoppingcartid=?";
		PreparedStatement pstmt;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			i=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
