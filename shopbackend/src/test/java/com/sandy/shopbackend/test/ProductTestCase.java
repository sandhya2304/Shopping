package com.sandy.shopbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sandy.shopbackend.dao.ProductDao;
import com.sandy.shopbackend.dto.Product;

public class ProductTestCase
{
	private static AnnotationConfigApplicationContext context;
	
	private static ProductDao productDao;
	
	Product product;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.sandy.Shopbackend");
		context.refresh();
		
		productDao=(ProductDao) context.getBean("productDao");
		
	}
	@Test
	public void testCrudProduct()
	{
		/*
		product=new Product();
		product.setName("Oppo Selfie");
		product.setBrand("Oppo");
		product.setDescription("this is oppro brand product");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("Something went wrong while insering the product",true,productDao.add(product));
		
		
		//reading and update the product
		productDao.get(1);
		product.setName("sandhya");
		
		assertEquals("Something went wrong while updating the product",true,productDao.update(product));

		//delete the product
		assertEquals("Something went wrong while delete the product",true,productDao.delete(product));
		
		//list the product
		assertEquals("Something went wrong while list the product",13,productDao.list().size());
		*/
		
	}
		/*@Test
		public void listActiveProduct()
		{
			assertEquals("Something went wrong while list the product",5,productDao.listActiveProducts().size());
			
		}
		
		@Test
		public void listActiveProductByCategory()
		{
			assertEquals("Something went wrong while list the product",3,productDao.listActiveProductsByCategory(3).size());
			assertEquals("Something went wrong while list the product",2,productDao.listActiveProductsByCategory(1).size());
			
		}

	
		@Test
		public void listlatestActiveProduct()
		{
			assertEquals("Something went wrong while list the product",3,productDao.getLatestActiveProducts(3).size());
			
		}*/

}
