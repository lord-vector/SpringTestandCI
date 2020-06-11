package com.stackroute.myrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy

public class ItemAOPConfig {

	
	@Bean
	
	public ItemAspect logall()
	{
		return new ItemAspect();
	}
	
}
