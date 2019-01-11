package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.ShoppingDao;
import com.entity.Shopping;
import com.entity.ShoppingCart;
import com.util.ConnectionFactory;

public class ShoppingDaoImpl implements ShoppingDao{

	@Override
	public int save(Shopping shopping) {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionFactory.getConnection();
		String sql="insert into Shopping values(?,?)";
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
	public List<Shopping> select() {
		// TODO Auto-generated method stub
		List<Shopping> list=new ArrayList<Shopping>();
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from Shopping ";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				Shopping shop=new Shopping();

				shop.setShoppingid(rs.getLong(1));
				shop.setShoppingname(rs.getString(2));
				list.add(shop);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Shopping> select(long id) {
		// TODO Auto-generated method stub
		List<Shopping> list=new ArrayList<Shopping>();
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from Shopping where shoppingid=?";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				Shopping shop=new Shopping();

				shop.setShoppingid(rs.getLong(1));
				shop.setShoppingname(rs.getString(2));
				list.add(shop);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	

}
