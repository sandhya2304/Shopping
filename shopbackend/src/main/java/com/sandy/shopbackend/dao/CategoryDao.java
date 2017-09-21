package com.sandy.shopbackend.dao;

import java.util.List;

import com.sandy.shopbackend.dto.Category;

public interface CategoryDao
{
	
	public Category get(int id);
	public List<Category> listCategory();
	boolean add(Category category);
	boolean delete(Category category);
	boolean update(Category category);

}
