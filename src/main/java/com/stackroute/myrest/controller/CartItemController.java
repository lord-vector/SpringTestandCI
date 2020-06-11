package com.stackroute.myrest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.myrest.exceptions.ItemAlreadyExistsException;
import com.stackroute.myrest.exceptions.ItemNotFoundException;
import com.stackroute.myrest.model.CartItem;
import com.stackroute.myrest.service.CartService;

@RestController
@ComponentScan(basePackages="com.stackroute.myrest")
@RequestMapping("item")

public class CartItemController {
	
	@Autowired
	CartService cartservice;
	
	@GetMapping("/showall")
	
	public ResponseEntity<?> callFirst()
	{
		return new ResponseEntity<String>("Welcome",HttpStatus.OK);
	}
	
	@PostMapping("/saveitem") // xml or json
	
//	@RequestMapping(value="/saveitem",method=RequestMethod.POST,consumes="application/json")
	
	public ResponseEntity<?> addCartItem(@RequestBody CartItem cart) throws ItemAlreadyExistsException
	{ 
		
		try
		{
		
		CartItem result=cartservice.saveCart(cart);
		return new ResponseEntity<CartItem>(result,HttpStatus.OK);
		
		}
		catch(ItemAlreadyExistsException item)
		{
			
			return new ResponseEntity<String>(item.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/showitems")
	
	public ResponseEntity<?> showItems()
	{
		List<CartItem> listcart=cartservice.viewCarts();
		return new ResponseEntity<List<CartItem>>(listcart,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	
	public ResponseEntity<?> deleteCart(@PathVariable("id") String id)
	{
		try
		{
		boolean result=cartservice.removeCart(id);
		return new ResponseEntity<String>("Successfully removed",HttpStatus.OK);
		
		}
		catch(ItemNotFoundException except)
		{
			return new ResponseEntity<String>("Item id not exist",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/modifycart")
	public ResponseEntity<?> modifycartt(@RequestBody CartItem cart)
	{
		try
		{
		CartItem modifiedcart=cartservice.updateCart(cart);
		return new ResponseEntity<CartItem>(modifiedcart,HttpStatus.OK);
		
		}
		catch(ItemNotFoundException except)
		{
			return new ResponseEntity<String>("Item id not exist",HttpStatus.NOT_FOUND);
		}
	}
}
