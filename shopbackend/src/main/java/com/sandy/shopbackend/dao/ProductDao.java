package com.sandy.shopbackend.dao;

import java.util.List;

import com.sandy.shopbackend.dto.Product;

public interface ProductDao 
{
	List<Product> list();
	Product get(int productId);
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	
	//business methods

	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int caregoryId);
	List<Product> getLatestActiveProducts(int count);
	
}
