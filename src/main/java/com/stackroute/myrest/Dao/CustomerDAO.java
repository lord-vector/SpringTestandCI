package com.stackroute.myrest.Dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.myrest.model.CartItem;
import com.stackroute.myrest.model.Customer;

@Repository
@Transactional
public class CustomerDAO {

	@Autowired
	SessionFactory sessfactory;
	
	public Customer addCustomer(Customer customer)
	{
	 
		sessfactory.getCurrentSession().save(customer);
	
		return customer;
		
	}
	
	public List<Customer> viewall()
	{
		return sessfactory.getCurrentSession().createQuery("from Customer").list();
	}
	
	
	public Customer findCustomer(String id)
	{
		return  (Customer)sessfactory.getCurrentSession().createQuery("from Customer where customerid='" + id +"'").uniqueResult();
	}
}
