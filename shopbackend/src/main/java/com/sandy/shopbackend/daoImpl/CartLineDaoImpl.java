package com.sandy.shopbackend.daoImpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sandy.shopbackend.dao.CartLineDao;
import com.sandy.shopbackend.dto.Cart;
import com.sandy.shopbackend.dto.CartLine;

@Repository("cartLineDao")
@Transactional
public class CartLineDaoImpl implements CartLineDao
{
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public CartLine get(int id) 
	{
		return sessionFactory.getCurrentSession().get(CartLine.class, id);
	}

	@Override
	public boolean add(CartLine cartLine)
	{
		try
		{
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(CartLine cartLine)
	{
		try
		{
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(CartLine cartLine)
	{
		try
		{
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<CartLine> listAll(int cartId)
	{
		String query="from CartLine  where cartId= :cartId";
		return sessionFactory.getCurrentSession()
				      .createQuery(query,CartLine.class)
				             .setParameter("cartId",cartId).getResultList();


		
	}

	@Override
	public List<CartLine> listAvailable(int cartId)
	{
		String query="from CartLine  where cartId= :cartId and available = :available";
		return sessionFactory.getCurrentSession()
				      .createQuery(query,CartLine.class)
				             .setParameter("cartId",cartId)
				             .setParameter("available", true)
				             .getResultList();

	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId)
	{

		String query="from CartLine  where cartId= :cartId and product.id = :productId";
		
		try
		{
		return sessionFactory.getCurrentSession()
				      .createQuery(query,CartLine.class)
				             .setParameter("cartId",cartId)
				             .setParameter("productId",productId)
				             .getSingleResult();
		}
		catch(Exception e){e.printStackTrace();}
		return null;

	}
	

	@Override
	public boolean updateCart(Cart cart) 
	{
		try
		{
			sessionFactory.getCurrentSession().update(cart);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	

}
