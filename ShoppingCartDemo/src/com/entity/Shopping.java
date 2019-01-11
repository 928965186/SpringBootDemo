package com.entity;

public class Shopping {
	private long shoppingid;
	private String shoppingname;
	public long getShoppingid() {
		return shoppingid;
	}
	public void setShoppingid(long shoppingid) {
		this.shoppingid = shoppingid;
	}
	public String getShoppingname() {
		return shoppingname;
	}
	public void setShoppingname(String shoppingname) {
		this.shoppingname = shoppingname;
	}
	public Shopping(String shoppingname) {
		super();
		this.shoppingname = shoppingname;
	}
	public Shopping() {
		super();
	}
	@Override
	public String toString() {
		return "Shopping [shoppingid=" + shoppingid + ", shoppingname=" + shoppingname + "]";
	}
	
	
}
