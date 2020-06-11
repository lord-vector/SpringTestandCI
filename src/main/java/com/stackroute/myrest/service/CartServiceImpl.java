package com.stackroute.myrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.myrest.Dao.CartDAO;
import com.stackroute.myrest.exceptions.ItemAlreadyExistsException;
import com.stackroute.myrest.exceptions.ItemNotFoundException;
import com.stackroute.myrest.model.CartItem;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDAO cartdao;
	
	public CartItem saveCart(CartItem catitem) throws ItemAlreadyExistsException {
		// TODO Auto-generated method stub
		
		CartItem item=findCardbyId(catitem.getCartid());
		
		if(item==null)
		
		catitem=cartdao.addCart(catitem);
		else
			throw new ItemAlreadyExistsException("Id alread exists");
		
		return catitem;
	}

	public CartItem findCardbyId(String id) {
		CartItem item=cartdao.findCart(id);
		return item;
	}

	public List<CartItem> viewCarts() {
		
		List list=cartdao.showcart();
		return list;
	}

	public boolean removeCart(String id) throws ItemNotFoundException{
		CartItem cart=cartdao.findCart(id);
		if(cart==null)
			throw new ItemNotFoundException();
		else
			cartdao.deleteCart(id);
		return true;
	}

	public CartItem updateCart(CartItem givencart) throws ItemNotFoundException {
	CartItem cart=cartdao.findCart(givencart.getCartid());
	if(cart==null)
		throw new ItemNotFoundException();
	else
		cartdao.modifyCart(givencart);
	return givencart;
	}

}
