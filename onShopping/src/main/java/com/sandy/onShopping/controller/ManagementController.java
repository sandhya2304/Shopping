package com.sandy.onShopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sandy.onShopping.util.FileUploadUtility;
import com.sandy.shopbackend.dao.*;
import com.sandy.shopbackend.dto.Category;
import com.sandy.shopbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController
{
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	private static final Logger logger=LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false)String operation)
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Manage Products");
		mv.addObject("UserClickManageProducts",true);
		
		Product nProduct=new Product();
		
		//set few fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product",nProduct);
		
		if(operation!=null)
		{
			if(operation.equals("product"))
			{
				mv.addObject("msg","Product submitted succesfuulyy!!!");
			}
		}
		
		return mv;
		
	}
	
	//HANDLING product submision
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product")Product mproduct,
			BindingResult results,Model model,HttpServletRequest request)
	{
		//check if there any errors

		if(results.hasErrors())
		{
			model.addAttribute("UserClickManageProducts",true);
			model.addAttribute("title","Manage Products");
					
			model.addAttribute("msg","Validation failed for product submission!!");
			
			
			return "page";
		}
		
		logger.info(mproduct.toString());
		
		//create a new product
		productDao.add(mproduct);
		
		if(!mproduct.getFile().getOriginalFilename().equals(""))
		{
			FileUploadUtility.uploadFile(request,mproduct.getFile(),mproduct.getCode());
			
		}
		
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories()
	{
		return categoryDao.listCategory();
	}
	
	

}
