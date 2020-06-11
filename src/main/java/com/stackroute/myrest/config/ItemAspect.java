package com.stackroute.myrest.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.stackroute.myrest.exceptions.ItemAlreadyExistsException;
import com.stackroute.myrest.model.CartItem;

@Aspect
@Component

public class ItemAspect {
	
	Logger mylogger=LoggerFactory.getLogger(ItemAspect.class);
	
	@Before("execution (* com.stackroute.myrest.controller.CartItemController.showItems(..))")
	
	public void displayDetail(JoinPoint jp)
	{
		mylogger.info("User Called Showitem Method");
		System.out.println("User called showitem");
	}
	
 @After("execution (* com.stackroute.myrest.controller.CartItemController.showItems(..))")
 
  public void afterExecute(JoinPoint jp)
  {
	 mylogger.info("after adding items");
	 System.out.println("After ShowItems");
  }
 
 @AfterThrowing(pointcut="mypointcut()",
		 throwing="except")
 
 public void afterThrow(ItemAlreadyExistsException except)
 {
	 //mylogger.info(");
	 
    System.out.println("Some user is trying to add same item");
 }
 
 
 @Around("mypointcut()")
 public Object insideAddItem(ProceedingJoinPoint proceedjp)
 {
	 Object object=null;
	 
	 try
	 {
		 object=proceedjp.proceed();
		 ResponseEntity response=(ResponseEntity)object;
		 CartItem cartitem=(CartItem)response.getBody();
		 
		 System.out.println("Some user added " + cartitem.getItemname());
		 System.out.println("Quantity  is " + cartitem.getQty());
		 System.out.println(response.toString());
		 mylogger.info("Some user added"+ cartitem.getItemname());;
	 }
	 catch(Throwable excep)
	 {
		 
	 }
	 
	 return object;
	 
 }
	
 @AfterReturning("mypointcut()")
 public void setClosure()
 {
	 System.out.println("Item added successfully ");
 }
 
 
 @Pointcut("execution (* com.stackroute.myrest.controller.CartItemController.addCartItem(..))")
 public void mypointcut()
 { 
 }
}
