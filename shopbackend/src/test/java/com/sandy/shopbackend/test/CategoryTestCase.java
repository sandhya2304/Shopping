package com.sandy.shopbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.InitBinder;

import com.sandy.shopbackend.dao.CategoryDao;
import com.sandy.shopbackend.dto.Category;

public class CategoryTestCase
{
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDao categoryDao;
	
	private Category category;
	
	
	@BeforeClass
	public static void Init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.sandy.shopbackend");
		context.refresh();
		
		categoryDao =(CategoryDao) context.getBean("categoryDao");
		
	}
	/*@Test
	public  void testaddCategory()
	{
		category=new Category();
		category.setName("Mobile");
		category.setDescription("decrption mobile");
		category.setImgUrl("catimg_112.png");
		
		assertEquals("suucesfully added the category inside the table",true,categoryDao.add(category));
		
	}
*/
	/*@Test
	public void testgetCategory()
	{
		category=categoryDao.get(2);
		assertEquals("suucesfully fetch the single category from the table!","Mobile",category.getName());
	}
	*/
	
	/*@Test
	public void testupdateCategory()
	{
		category=categoryDao.get(2);
		category.setName("LAptop");
		assertEquals("suucesfully update the single category in the table!",true,categoryDao.update(category));
	}
	*/
	/*@Test
	public void testdleteCategory()
	{
		category=categoryDao.get(2);
		assertEquals("suucesfully delete the single category in the table!",true,categoryDao.delete(category));
	}*/
	
	/*@Test
	public void testlistCategory()
	{
		assertEquals("suucesfully fetch  the list of categories from the table!",1,categoryDao.listCategory().size());
	}
	*/
	@Test
	public void testcrudCategory()
	{
		//add opertaion
		category=new Category();
		category.setName("Mobile");
		category.setDescription("decrption mobile");
		category.setImgUrl("catimg_mob.png");
		
		assertEquals("suucesfully added the category inside the table",true,categoryDao.add(category));
		
		category=new Category();
		category.setName("washing");
		category.setDescription("decrption mobile");
		category.setImgUrl("catimg_wash.png");
		
		assertEquals("suucesfully added the category inside the table",true,categoryDao.add(category));
		
		//fetching and update category
		category=categoryDao.get(2);
		category.setName("Cooler");
		assertEquals("suucesfully update the single category in the table!",true,categoryDao.update(category));
		
		
		//delete the category
		assertEquals("suucesfully delete the single category in the table!",true,categoryDao.delete(category));
		
		//fetch the category
		assertEquals("suucesfully fetch  the list of categories from the table!",1,categoryDao.listCategory().size());
		
	}
	
}
