package com.stackroute.myrest.service;

import java.util.List;

import com.stackroute.myrest.exceptions.ItemAlreadyExistsException;
import com.stackroute.myrest.exceptions.ItemNotFoundException;
import com.stackroute.myrest.model.CartItem;

public interface CartService {

	CartItem saveCart(CartItem catitem) throws ItemAlreadyExistsException;
	CartItem findCardbyId(String id);
	List<CartItem> viewCarts();
	boolean removeCart(String id) throws ItemNotFoundException;
	CartItem updateCart(CartItem cart) throws ItemNotFoundException;
}
