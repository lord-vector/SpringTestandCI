package com.stackroute.myrest.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.myrest.Dao.CartDAO;
import com.stackroute.myrest.exceptions.ItemAlreadyExistsException;
import com.stackroute.myrest.exceptions.ItemNotFoundException;
import com.stackroute.myrest.model.CartItem;

import junit.framework.Assert;

public class ItemServiceTest {

	@Mock
    CartDAO cartdao;

@InjectMocks
CartServiceImpl cartservice;


CartItem cartitem;

@Before
public void init() throws Exception
{
	MockitoAnnotations.initMocks(this);
	cartitem=new CartItem();
	cartitem.setCartid("c112");
	cartitem.setItemname("Mobile");
	cartitem.setQty(300);
	
}

@Test
public void whenaddItemsthenreturnitem () throws ItemAlreadyExistsException
{
	
	Mockito.when(cartservice.saveCart(cartitem)).thenReturn(cartitem);
	
	CartItem cart=cartservice.saveCart(cartitem);
	
	Assert.assertEquals(cartitem, cart);
	
	
	
	
}


@Test 
public void whensearchbyIdthenReturnifexists() throws ItemNotFoundException
{
	Mockito.when(cartservice.findCardbyId("c112")).thenReturn(cartitem);
  
	 CartItem cart=cartservice.findCardbyId("c112");
	 

	 Assert.assertEquals(cartitem, cart);
}

@Test
public void whensearchbynullthenreturnnull() throws ItemNotFoundException
{
 Mockito.when(cartservice.findCardbyId("3988")).thenReturn(null);
   CartItem cart=cartservice.findCardbyId("3988");
   
   Assert.assertEquals(null, cart);
 
}





}
