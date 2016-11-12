package com.bestchoice.util;

import java.util.*;
import org.hibernate.*;
import org.hibernate.criterion.Projections;

import com.bestchoice.model.Products;

/**
 * @author PRITAM. Created for loading list from database table.
 *
 */
public class Loadlist {

	/**
	 * This method loads list from database table Product List. Hibernate is
	 * used as an ORM tool for fetching data from table.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Products> loadProductList() {
		// Get session factory object initialized in Listener.java and create
		// session object
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Simple HQL for fetching list
		Query query = session.createQuery("from Products");
		ArrayList<Products> ar = new ArrayList<>();
		try {
			ar = (ArrayList<Products>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		session.close();
		return ar;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Products> fetchProducts(List<String> desiredList) {
		// Get session factory object initialized in Listener.java and create
		// session object
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Simple HQL for fetching list
		Query query = session.createQuery("from Products where productName in (:list)");
		query.setParameterList("list", desiredList);
		ArrayList<Products> ar = new ArrayList<>();
		try {
			ar = (ArrayList<Products>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		session.close();
		return ar;
	}

	@SuppressWarnings("unchecked")
	public List<String> fetchProductNames() {
		// Get session factory object initialized in Listener.java and create
		// session object
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria critiera = session.createCriteria(Products.class).setProjection(
				Projections.distinct(Projections.projectionList().add(Projections.property("productName"))));
		List<String> prodNameList = critiera.list();
		session.close();
		return prodNameList;
	}

}
