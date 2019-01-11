package com.entity;

public class ShoppingCart {
	
	private Long shoppingcartid;
	private String shoppingcartname;
	public Long getShoppingcartid() {
		return shoppingcartid;
	}
	public void setShoppingcartid(Long shoppingcartid) {
		this.shoppingcartid = shoppingcartid;
	}
	public String getShoppingcartname() {
		return shoppingcartname;
	}
	public void setShoppingcartname(String shoppingcartname) {
		this.shoppingcartname = shoppingcartname;
	}
	public ShoppingCart(String shoppingcartname) {
		super();
		this.shoppingcartname = shoppingcartname;
	}
	public ShoppingCart() {
		super();
	}
	@Override
	public String toString() {
		return "ShoppingCart [shoppingcartid=" + shoppingcartid + ", shoppingcartname=" + shoppingcartname + "]";
	}
	

}
