package com.stackroute.myrest.service;

import java.util.List;

import com.stackroute.myrest.exceptions.ItemNotFoundException;
import com.stackroute.myrest.model.Customer;

public interface CustomerService {

	Customer addcustomer(Customer customer) throws ItemNotFoundException;
	List<Customer> viewAll();
	Customer findByCustomerid(String id);
}
