package com.sandy.shopbackend.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sandy.shopbackend.dao.CategoryDao;
import com.sandy.shopbackend.dto.Category;


@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao
{
	private static List<Category> categories=new ArrayList<Category>();
	
	static
	{
		Category c=new Category();
		c.setId(1);
		c.setName("Television");
		c.setDescription("decrption my tv");
		c.setImgUrl("catimg_1.png");
		
		Category c1=new Category();
		c1.setId(2);
		c1.setName("machine");
		c1.setDescription("decrption my machine");
		c1.setImgUrl("catimg_2.png");
		
		Category c2=new Category();
		c2.setId(3);
		c2.setName("Mobile");
		c2.setDescription("decrption my mobile");
		c2.setImgUrl("catimg_3.png");
		
		categories.add(c);
		categories.add(c1);
		categories.add(c2);
		
	}
	

	@Override
	public List<Category> listCategory() 
	{
		
		return categories;
	}


	@Override
	public Category get(int id) {
		
		for(Category category : categories  ) 
		{
			if(category.getId() == id) return category;
		}
		
		return null;
	}
	

}
