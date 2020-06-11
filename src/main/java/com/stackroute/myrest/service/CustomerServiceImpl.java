package com.stackroute.myrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.myrest.Dao.CartDAO;
import com.stackroute.myrest.Dao.CustomerDAO;
import com.stackroute.myrest.exceptions.ItemNotFoundException;
import com.stackroute.myrest.model.CartItem;
import com.stackroute.myrest.model.Customer;


@Service

public class CustomerServiceImpl implements CustomerService{

	 @Autowired
	 CustomerDAO custdao;
	 
	 @Autowired
	 CartDAO cart;
	
	public Customer addcustomer(Customer customer) throws ItemNotFoundException {
		
		CartItem cartitem=customer.getCart();
		
		CartItem checkedcart=cart.findCart(cartitem.getCartid());
		
		if(checkedcart==null)
			throw new ItemNotFoundException();
		else
			custdao.addCustomer(customer);
		
		return customer;
	}

	public List<Customer> viewAll() {
	return custdao.viewall();
	}

	public Customer findByCustomerid(String id) {
		
		return custdao.findCustomer(id);
	}

}
