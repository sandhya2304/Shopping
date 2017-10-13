package com.sandy.onShopping.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandy.onShopping.model.UserModel;
import com.sandy.shopbackend.dao.*;
import com.sandy.shopbackend.dto.Cart;
import com.sandy.shopbackend.dto.CartLine;
import com.sandy.shopbackend.dto.Product;

@Service("cartService")
public class CartService
{
	
	@Autowired
	private CartLineDao cartLineDao;
	
	@Autowired
	private ProductDao productDao;
	
    @Autowired
	private HttpSession session;
    
    
    //return the cart of the user who has logged in
    public Cart getCart()
    {
    	return ((UserModel)session.getAttribute("userModel")).getCart();
    }
	
    
    //return the entire cart lines
    public List<CartLine> getCartLines()
    {
    	
    	return cartLineDao.listAll(this.getCart().getId());
    	
    }


	public String updateCartLine(int cartLineId,int count) 
	{
		CartLine cartLine=cartLineDao.get(cartLineId);
		
		if(cartLine == null)
		{
			return "result=error";
		}
		else
		{
			Product product=cartLine.getProduct();
			
			double oldTotal=cartLine.getTotal();
			
			if(product.getQuantity() <= count)
			{
				count=product.getQuantity();
				
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			
			cartLine.setTotal(product.getUnitPrice() * count);
			
			cartLineDao.update(cartLine);
			
			Cart cart=this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			
			cartLineDao.updateCart(cart);
			
			
			return "result=updated";
			
		}
		
		
	}


	public String deleteCartLine(int cartLineId)
	{
		// fecth the cartline
		
		CartLine cartLine=cartLineDao.get(cartLineId);
		
		if(cartLine==null)
		{
			return "result=error";
		}
		
		//update the cart
		
		Cart cart=this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() -1);
		
		cartLineDao.updateCart(cart);
		
		
		//remove the cartLine
		cartLineDao.delete(cartLine);
		
		
		return "result=deleted";
	}


	public String addCartLine(int productId)
	{
		
		String response=null;
		
		Cart cart=this.getCart();
		
		
		CartLine cartLine=cartLineDao.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine==null)
		{
			// add a new cartLine
			cartLine=new CartLine();
			
			
			//fecth the product
			Product product=productDao.get(productId);
			
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setProductCount(1);
            cartLine.setTotal(product.getUnitPrice());
            cartLine.setAvailable(true);
            
            cartLineDao.add(cartLine);
            
            cart.setCartLines(cart.getCartLines() +1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDao.updateCart(cart);
			
			response="result=added";
			
			
			
		}
		
		return response;
		
	}
	
	
}
