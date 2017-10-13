package com.sandy.shopbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sandy.shopbackend.dao.CartLineDao;
import com.sandy.shopbackend.dao.ProductDao;
import com.sandy.shopbackend.dao.UserDao;
import com.sandy.shopbackend.dto.*;

public class CartLineTestCase 
{
	
	private static AnnotationConfigApplicationContext context;
	private static CartLineDao cartLineDao;
	private static UserDao userDao;
	private static ProductDao productDao;
	
	
	private Product product=null;
	private Cart cart=null;
	private CartLine cartLine=null;
	private User user=null;
	
	

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sandy.shopbackend");
		context.refresh();
		
		userDao = (UserDao) context.getBean("userDao");
		productDao=(ProductDao) context.getBean("productDao");
		cartLineDao=(CartLineDao) context.getBean("cartLineDao");
		
		
	}
	
	
	@Test
	public void testAddNewCartLine()
	{
		//1. get the user
		user=userDao.getByEmail("ram@gmail.com");
		
		//2.fetch the cart
		cart=user.getCart();
		
		//3.get the product
		product=productDao.get(1);
		
		//4.create a new cartLine
		cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitPrice());
		
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("failed to add the cartLine",true,cartLineDao.add(cartLine));
		
		//5. update the cart
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		
		cart.setCartLines(cart.getCartLines() + 1);
		
		assertEquals("failed to update the cart",true,cartLineDao.updateCart(cart));
	}
    

}
