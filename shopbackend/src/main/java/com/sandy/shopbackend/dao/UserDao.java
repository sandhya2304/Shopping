package com.sandy.shopbackend.dao;

import java.util.List;

import com.sandy.shopbackend.dto.*;

public interface UserDao
{
	
	//add an user
	boolean addUser(User user);
	
	User getByEmail(String email);
	
	//add an address
		boolean addAddress(Address address);
		//alterntive
         //Address getBillingAddress(int userid);
	     //List<Address> getShippingAddress(int userid);
		
		//single address
		Address getBillingAddress(User user);
		
		List<Address> getShippingAddress(User user);
		
		//update an cart
		boolean updateCart(Cart cart);

}
