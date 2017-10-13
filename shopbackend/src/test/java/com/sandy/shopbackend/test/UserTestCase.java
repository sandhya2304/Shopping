package com.sandy.shopbackend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sandy.shopbackend.dao.UserDao;
import com.sandy.shopbackend.dto.*;



public class UserTestCase
{
	

	private static AnnotationConfigApplicationContext context;
	private static UserDao userDao;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sandy.shopbackend");
		context.refresh();
		
		userDao = (UserDao) context.getBean("userDao");
	}
    
    
	/*@Test
	public void testAddUser() 
	{
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		//add the user
		assertEquals("failed to add user!",true,userDao.addUser(user));
		
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		//link the user with the address using user id
		address.setUserId(user.getId());
		

		//add the address
		assertEquals("failed to add address!",true,userDao.addAddress(address));
		
		if(user.getRole().equals("USER"))
		{
			
			//create a cart for the user
			
			cart=new Cart();
			cart.setUser(user);
			
			//add the cart
			assertEquals("Failed to add cart!",true,userDao.addCart(cart));
			
			//add a shipping address for the user
			
			address = new Address();
			address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
			address.setAddressLineTwo("Near Kudrat Store");
			address.setCity("Mumbai");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("400001");
			//set shipping to true
			address.setShipping(true);
			
			//link it with the user
			address.setUserId(user.getId());
			
			
			//add the shipping address
			assertEquals("Failed to add the shipping address!", true, userDao.addAddress(address));
			
			
		}
		
	}*/
	
/*	@Test
	public void testAddUser() 
	{
		user = new User() ;
		user.setFirstName("Ram");
		user.setLastName("Sharma");
		user.setEmail("ram@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
				
		if(user.getRole().equals("USER"))
		{
			
			//create a cart for the user
			
			cart=new Cart();
			cart.setUser(user);
			
			//attach the cart with the user
			user.setCart(cart);
		
		}
		//add the user
		assertEquals("failed to add user!",true,userDao.addUser(user));		
	}*/
   /* @Test
	public void testUpdateCart()
	{
    	//fetch the user by its email
		user=userDao.getByEmail("ram@gmail.com");
    	
		//get the cart of the user
		cart=user.getCart();
		
		cart.setGrandTotal(5555);
		
		cart.setCartLines(2);
		
		//add the cart
		assertEquals("failed to add cart!",true,userDao.updateCart(cart));		
		
	}*/
	
	/*@Test
	public void testAddAddress()
	{
		//for single address fetch
		//we need to add an user
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		//add the user
		assertEquals("failed to add user!",true,userDao.addUser(user));
	
		//we are going to add the address
		

		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		//attach the uer to the address
		address.setUser(user);
		assertEquals("failed to add address!",true,userDao.addAddress(address));
	
		//we are also going to add the shipping address
		
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		//set shipping to true
		address.setShipping(true);
		
		//attach the uer to the address
				address.setUser(user);
				assertEquals("failed to add shipping address!",true,userDao.addAddress(address));
		
		
	}*/
	/*@Test
	public void testAddAddress()
	{
		//for multiple address fetch
		
		//fetch the user by its email
				user=userDao.getByEmail("ram@gmail.com");
	
		//we are also going to add the shipping address
		
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("delhi");
		address.setState("up");
		address.setCountry("India");
		address.setPostalCode("400001");
		//set shipping to true
		address.setShipping(true);
		
		//attach the uer to the address
				address.setUser(user);
				assertEquals("failed to add shipping address!",true,userDao.addAddress(address));
		
		
	}*/
	
	@Test
	public void testGetAddress()
	{
		//fetch the user by its email
		user=userDao.getByEmail("ram@gmail.com");
		assertEquals("failed to fetch list oof address!",1,userDao.getShippingAddress(user).size());
		
		assertEquals("failed to fecth list biling address!","delhi",userDao.getBillingAddress(user).getCity());
	}
	
}
