package com.sandy.onShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sandy.shopbackend.dao.ProductDao;
import com.sandy.shopbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController
{
	
	@Autowired
	private ProductDao productDao;

	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProduct()
	{
		return productDao.listActiveProducts();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductByCategory(@PathVariable int id)
	{
		return productDao.listActiveProductsByCategory(id);
	}
}
