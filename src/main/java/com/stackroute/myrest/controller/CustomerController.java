package com.stackroute.myrest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.myrest.exceptions.ItemNotFoundException;
import com.stackroute.myrest.model.Customer;
import com.stackroute.myrest.service.CustomerService;

@RestController
 
@RequestMapping("customer")

public class CustomerController {
	
	@Autowired
	CustomerService custservice;

	@PostMapping("/savecustomer")
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer,HttpSession session)
	{
		
		session.setAttribute("mycustomerid", customer.getCustomerid());
		try
		{
		Customer custresult=custservice.addcustomer(customer);
		return new ResponseEntity<Customer>(custresult,HttpStatus.OK);
			
		}
		catch(ItemNotFoundException itemexcep)
		
		{
			return new ResponseEntity<String>("Item not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/showcustomer/{id}")
	public ResponseEntity<?> showCustomer(@PathVariable("id") String id)
	{
		Customer customer=custservice.findByCustomerid(id);
		if(customer==null)
			return new ResponseEntity<String>("Customer id not found",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
}
