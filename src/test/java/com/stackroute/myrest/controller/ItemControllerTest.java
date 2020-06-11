package com.stackroute.myrest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.myrest.config.HibernateConfig;
import com.stackroute.myrest.model.CartItem;
import com.stackroute.myrest.service.CartService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {HibernateConfig.class})
@WebAppConfiguration

public class ItemControllerTest {

	MockMvc mockmvc;
	
	@Mock
	CartService cartservice;
	
	@InjectMocks
	CartItemController itemcontroller;
	
	CartItem cartitem;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		
		mockmvc=MockMvcBuilders.standaloneSetup(itemcontroller).build();
		
		cartitem=new CartItem();
		cartitem.setCartid("C8888");
		cartitem.setItemname("Windows10");
		cartitem.setQty(5);
			
	}
	
	@Test
	public void whecallsaveitemapithenreturnok() throws Exception
	{
		
		mockmvc.perform(MockMvcRequestBuilders.post("/item/saveitem")
				.contentType(MediaType.APPLICATION_JSON)
				.content(myconverter(cartitem)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());
		
		
	}
	
	
	
	public String myconverter(Object object) throws JsonProcessingException
	{
		String result;
		final ObjectMapper mapper=new ObjectMapper();
		result=mapper.writeValueAsString(object);
		return result;
		
	}
	
	
	
	
}
