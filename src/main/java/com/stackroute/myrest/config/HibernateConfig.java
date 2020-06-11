package com.stackroute.myrest.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.myrest.model.CartItem;
import com.stackroute.myrest.model.Customer;

 


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.stackroute.myrest")
@EnableWebMvc

public class HibernateConfig {

	 @Bean
	 public DataSource setDataSource()
	 {
		 BasicDataSource datasource=new BasicDataSource();
		 datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		 datasource.setUrl("jdbc:mysql://"+ System.getenv("MYSQL_HOST")+
//				 ":3306/"+ System.getenv("MYSQL_DATABASE") +
//				 "?createDatabaseIfNotExist=true&verifyServerCertificate=false"
//				 + "&useSSL=true&requireSSL=false&allowPublicKeyRetrieval=true&user=root&password=password");
//		 
	
		 datasource.setUrl(System.getenv("MYSQL_URL"));
		 
//		 datasource.setUsername("root");
//		 datasource.setPassword("password");
		 return datasource;
	 }
	@Bean
 public LocalSessionFactoryBean getSession(DataSource datasource) throws IOException
 
 {
	 LocalSessionFactoryBean sessionfactory=new LocalSessionFactoryBean();
	sessionfactory.setDataSource(datasource);
	
	Properties property=new Properties();
	property.put("hibernate.show_sql", "true");
	property.put("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect" );
	property.put("hibernate.hbm2ddl.auto", "update");
	
	sessionfactory.setHibernateProperties(property);
	
	sessionfactory.setAnnotatedClasses(Customer.class,CartItem.class);
		sessionfactory.afterPropertiesSet();
	
	return sessionfactory;
	 }
 
 @Bean
 
 public HibernateTransactionManager gettransact(SessionFactory sessionfactory)
 {
	 HibernateTransactionManager hibermanager=new HibernateTransactionManager();
	 hibermanager.setSessionFactory(sessionfactory);
	 return hibermanager;
 }


}


