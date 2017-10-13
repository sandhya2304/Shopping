package com.sandy.shopbackend.dao;

import java.util.List;

import com.sandy.shopbackend.dto.Cart;
import com.sandy.shopbackend.dto.CartLine;

public interface CartLineDao
{
	
	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	
	public List<CartLine> listAll(int cartId);
	
	//other business method related to cartLine
	
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int cartId,int productId);
	

	//update an cart
	boolean updateCart(Cart cart);

	

}
