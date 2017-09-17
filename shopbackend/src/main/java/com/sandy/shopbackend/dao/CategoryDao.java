package com.sandy.shopbackend.dao;

import java.util.List;

import com.sandy.shopbackend.dto.Category;

public interface CategoryDao
{
	public List<Category> listCategory();
	public Category get(int id);

}
