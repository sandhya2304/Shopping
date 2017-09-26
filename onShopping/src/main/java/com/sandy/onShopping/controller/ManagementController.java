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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sandy.onShopping.util.FileUploadUtility;
import com.sandy.onShopping.validator.ProductValidator;
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
			else if(operation.equals("category"))
			{
				mv.addObject("msg","new Category submitted succesfuulyy!!!");
			}
		}
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id)
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Manage Products");
		mv.addObject("UserClickManageProducts",true);
		
		//fetch the product from the databse
		Product nProduct=productDao.get(id);
		//set the product fetch from databse
		mv.addObject("product",nProduct);
	
		
		return mv;
		
	}
	
	
	//HANDLING product submision
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product")Product mproduct,
			BindingResult results,Model model,HttpServletRequest request)
	{
		//handle image validation fro new products
		if(mproduct.getId() == 0)
		{
			new ProductValidator().validate(mproduct, results);
		}
		else
		{
			if(!mproduct.getFile().getOriginalFilename().equals(""))
			{
				new ProductValidator().validate(mproduct, results);
			}
		}
		
		//check if there any errors

		if(results.hasErrors())
		{
			model.addAttribute("UserClickManageProducts",true);
			model.addAttribute("title","Manage Products");
					
			model.addAttribute("msg","Validation failed for product submission!!");
			
			
			return "page";
		}
		
		logger.info(mproduct.toString());
		
		if(mproduct.getId() == 0)
		{
			//create a new product
			productDao.add(mproduct);
		}
		else
		{
			//update the product id is not 0
			productDao.update(mproduct);
		}
	
		if(!mproduct.getFile().getOriginalFilename().equals(""))
		{
			FileUploadUtility.uploadFile(request,mproduct.getFile(),mproduct.getCode());
			
		}
		
		
		return "redirect:/manage/products?operation=product";
	}

   @RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
   @ResponseBody
	public String handleProductActivation(@PathVariable int id)
	{
	   //is going to fetch product from db
		Product product=productDao.get(id);
		boolean isActive = product.isActive();
		//activating and deactivating based on the value of active field
		product.setActive(!product.isActive());
		//updating the product
		productDao.update(product);
		
		
		return (isActive)? 
				"you have succefully deactivate the product with id " + product.getId()
		         : "you have succefully activate the product with id " + product.getId() ;
	}
   @RequestMapping(value="/category", method=RequestMethod.POST)
   public String handleCategorySubmission(@ModelAttribute Category category )
   {
	   // add new category
	   categoryDao.add(category);
	   
	   return "redirect:/manage/products?operation=category";
   }
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories()
	{
		return categoryDao.listCategory();
	}
	
	@ModelAttribute("category")
	public Category getCategory()
	{
		return new Category();
	}
	
	

}
