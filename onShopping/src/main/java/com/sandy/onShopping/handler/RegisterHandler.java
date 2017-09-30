package com.sandy.onShopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.sandy.onShopping.model.RegisterModel;
import com.sandy.shopbackend.dao.UserDao;
import com.sandy.shopbackend.dto.*;



@Component
public class RegisterHandler 
{
	@Autowired
	private UserDao userDao;
	
	public RegisterModel init()
	{
		
		return new RegisterModel();
	}
	
	
	public void addUser(RegisterModel registerModel,User user)
	{
		registerModel.setUser(user);	
	}
	
	public void addBilling(RegisterModel registerModel,Address billing)
	{
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model)
	{
		String tansitionValue="success";
		
		//fetch the user
		
		User user=model.getUser();
		if(user.getRole().equals("USER"))
		{
			Cart cart=new Cart();
			cart.setUser(user);
			user.setCart(cart);
	}
		
		//save the user
		userDao.addUser(user);
		
		//get the address
		Address billing=model.getBilling();
		billing.setUser(user);
		billing.setBilling(true);
		
		//save the address
		userDao.addAddress(billing);
		
		
		return tansitionValue;
	}

	public String validateUser(User user, MessageContext error) {
		  String transitionValue = "success";
		  
		   if(!user.getPassword().equals(user.getConfirmPassword())) {
		    error.addMessage(new MessageBuilder()
		    		.error()
		    		.source("confirmPassword")
		    		.defaultText("Password does not match confirm password!")
		    		.build());
		    
		    transitionValue = "failure";    
		   }  
		   
		   if(userDao.getByEmail(user.getEmail())!=null)
		   {
		    error.addMessage(new MessageBuilder()
		    		.error().source("email")
		    		.defaultText("Email address is already taken!")
		    		.build());
		    
		    transitionValue = "failure";
		   }
		   
		  return transitionValue;
		 }
}
