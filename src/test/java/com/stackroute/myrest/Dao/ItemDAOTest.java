package com.stackroute.myrest.Dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.myrest.config.HibernateConfig;
import com.stackroute.myrest.model.CartItem;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes= {HibernateConfig.class})
@TestExecutionListeners( {DependencyInjectionTestExecutionListener.class,TransactionalTestExecutionListener.class})

public class ItemDAOTest {

	@Autowired
	SessionFactory sessfact;
	
	CartDAO cartdao;
	
	CartItem cartitem;
	
	@Before
	public void init()
	{
		cartdao=new CartDAO(sessfact);
		cartitem=new CartItem();
		cartitem.setCartid("C900");
		cartitem.setItemname("Wifi Device");
		cartitem.setQty(45);
		
	}
	
	@Test
	public void whenadditemthenreturnsame()
	{
		CartItem inserteditem=cartdao.addCart(cartitem);
		
		CartItem result=cartdao.findCart("C900");
		
		Assert.assertEquals(result, inserteditem);
		
	}
	
@After	
	public void tearOff()
	{
	
	//cartdao.deleteCart("C900");
	
	Query query=sessfact.getCurrentSession().createQuery("DELETE from CartItem where cartid='C900'");
	int a=query.executeUpdate();
	}
	

}
