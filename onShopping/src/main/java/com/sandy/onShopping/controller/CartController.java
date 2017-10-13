package com.sandy.onShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sandy.onShopping.Service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController
{
	@Autowired
	private CartService cartService;
	
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result",required=false)String result)
	{
		
		ModelAndView mv=new ModelAndView("page");
		
		  if(result!=null)
		  {
			  
			  switch(result)
			  {
			    case "updated":
			    	mv.addObject("msg","cart line has been updated");
			    	break;
			    case "added":
			    	mv.addObject("msg","cart line has been added");
			    	break;	
			    	
			    case "error":
			    	mv.addObject("msg","cart line has been shwoing an error");
			    	break;
			    case "deleted":
			    	mv.addObject("msg","cart line has been removed succesully");
			    	break;
			  }
		  }
		
		mv.addObject("title","User cart");
		mv.addObject("userClickShowCart",true);
		mv.addObject("cartLine",cartService.getCartLines());
		
		
		return mv;
		
	}
	 
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId,@RequestParam int count)
	{
		String response=cartService.updateCartLine(cartLineId , count);
		
		
		return "redirect:/cart/show?"+response;
	}
	

	@RequestMapping("/{cartLineId}/delete")
	public String deleteCart(@PathVariable int cartLineId)
	{
		String response=cartService.deleteCartLine(cartLineId);
		
		
		return "redirect:/cart/show?"+response;
	}
	
	
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId)
	{
		String response=cartService.addCartLine(productId);
		
		
		return "redirect:/cart/show?"+response;
	}

}
