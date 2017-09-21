package com.sandy.shopbackend.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sandy.shopbackend.dao.CategoryDao;
import com.sandy.shopbackend.dto.Category;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	private static List<Category> categories = new ArrayList<Category>();
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> listCategory()
	{
		String selectActiveCategory="from Category where active =:x";
		Query query=sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("x",true);

		return query.getResultList();
	}

	// getting single ctageory based on id
	@Override
	public Category get(int id) {

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override

	public boolean add(Category category) {
		try {
			// add category to the database
			sessionFactory.getCurrentSession().persist(category);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);
		try {
			// delete category to the database
			sessionFactory.getCurrentSession().update(category);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//update a single category
	@Override
	public boolean update(Category category) {
		try {
			// update category to the database
			sessionFactory.getCurrentSession().update(category);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
