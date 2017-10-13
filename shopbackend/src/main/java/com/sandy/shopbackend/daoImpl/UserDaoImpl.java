package com.sandy.shopbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sandy.shopbackend.dao.UserDao;
import com.sandy.shopbackend.dto.Address;
import com.sandy.shopbackend.dto.Cart;
import com.sandy.shopbackend.dto.User;


@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public boolean addUser(User user)
	{
		try
		{
			sessionFactory.getCurrentSession().persist(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean addAddress(Address address) 
	{
		try
		{
			sessionFactory.getCurrentSession().persist(address);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public User getByEmail(String email) 
	{
		String query="from User where email =:email";
		try
		{		
			return sessionFactory.getCurrentSession()
					     .createQuery(query,User.class)
					          .setParameter("email",email).getSingleResult();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Address getBillingAddress(User user)
	{
		String selectQuery = "FROM Address WHERE user = :user AND billing = :isBilling";
		try{
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("user", user)
						.setParameter("isBilling", true)
						.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public List<Address> getShippingAddress(User user)
	{
         String query="from Address where user= :user and shipping= :shipping";
		
		try
		{
			return sessionFactory.getCurrentSession()
					.createQuery(query,Address.class)
					     .setParameter("user",user)
					       .setParameter("shipping",true)
					         .getResultList();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	

}
