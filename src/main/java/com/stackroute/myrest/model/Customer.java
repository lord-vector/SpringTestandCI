package com.stackroute.myrest.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Customer {

	@Id
	String customerid;
	
	String customername;
	@ManyToOne
	CartItem cart;
	
	
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public CartItem getCart() {
		return cart;
	}
	public void setCart(CartItem cart) {
		this.cart = cart;
	}
	
	
}
