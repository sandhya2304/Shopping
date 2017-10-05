package com.sandy.onShopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sandy.onShopping.model.UserModel;
import com.sandy.shopbackend.dao.UserDao;
import com.sandy.shopbackend.dto.User;

@ControllerAdvice
public class GlobalController
{
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDao userDao;
	
	private UserModel userModel=null;
	
	@ModelAttribute("userModel")
	public UserModel getModel()
	{
		if(session.getAttribute("userModel")==null)
		{
			//add the user model
			Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
			
			User user=userDao.getByEmail(authentication.getName());
			if(user!=null)
			{
				//create the user model object to pass the user details
				
				userModel=new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullname(user.getFirstName() + " " +user.getLastName());
				
			   if( userModel.getRole().equals("USER"))
			   {
				   
				   //set the cart only if the user is a buyer
				   userModel.setCart(user.getCart());
				   
			   }
			   
			   //set the usermodel in the session
			   session.setAttribute("userModel",userModel);
			   
			   return userModel;
			
			}
			
		}
		
		return (UserModel) session.getAttribute("userModel");
		
	}
	

}
