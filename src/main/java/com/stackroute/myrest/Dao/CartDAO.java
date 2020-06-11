package com.stackroute.myrest.Dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.myrest.model.CartItem;


@Transactional
@Repository
public class CartDAO {

	@Autowired
	SessionFactory sessfactory;
	
	public CartDAO()
	{
		
	}
	
	public CartDAO(SessionFactory sessfact)
	{
		this.sessfactory=sessfact;
		
	}
	public CartItem addCart(CartItem cartitem)
	{
		sessfactory.getCurrentSession().save(cartitem);
		return cartitem;
	}
	
	public CartItem findCart(String id)
	{
	CartItem cartiem=(CartItem)sessfactory.getCurrentSession().createQuery("from CartItem where cartid='"+id +"'").uniqueResult();
	return cartiem;
	}
	
	public List showcart()
	{
		return sessfactory.getCurrentSession().createQuery("from CartItem").list();
	}
	
	public boolean deleteCart(String id)
	{
		CartItem item=findCart(id);
		
		sessfactory.getCurrentSession().delete(item);
		return true;
	}
	
	
	public CartItem modifyCart(CartItem givencart)
	{
		
		CartItem cart=findCart(givencart.getCartid());
		 if(cart!=null)
		 {
			 cart.setItemname(givencart.getItemname());
			 cart.setQty(givencart.getQty());
			 sessfactory.getCurrentSession().update(cart);
		 }
		 return cart;
		
	}
	
}
