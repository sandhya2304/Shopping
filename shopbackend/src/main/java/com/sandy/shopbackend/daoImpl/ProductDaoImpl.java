package com.sandy.shopbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sandy.shopbackend.dao.ProductDao;
import com.sandy.shopbackend.dto.Product;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> list() {

		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product" , Product.class)
				               .getResultList();

	}

	/**
	 * single product
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * INSERT
	 */
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * UODATE
	 */

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
try {
			
			product.setActive(false);
			// call the update method
			return this.update(product);
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;			
	}
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProduct = "from Product WHERE active =:x";
		return sessionFactory
				       .getCurrentSession()
				              .createQuery(selectActiveProduct)
				                .setParameter("x",true)
				                      .getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int caregoryId)
	{
		String selectActiveProductByCategory = "from Product WHERE active =:x and categoryId =:catgeoryId";
		return sessionFactory
				       .getCurrentSession()
				              .createQuery(selectActiveProductByCategory,Product.class)
				                .setParameter("x",true)
				                .setParameter("catgeoryId",caregoryId)
				                      .getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count)
	{
		return sessionFactory
			       .getCurrentSession()
			              .createQuery("from Product where active =:x order by id",Product.class)
			                .setParameter("x", true)
			                  .setFirstResult(0)
			                    .setMaxResults(count)
			                      .getResultList();
	}

}
