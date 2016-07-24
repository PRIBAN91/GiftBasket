package com.bestchoice.util;

import java.util.*;
import org.hibernate.*;
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
		System.out.println("Inside load method :: ");
		// Get sessionfactory object initialized in Listener.java and create
		// session object
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Simple HQL for fetching list
		Query query = session.createQuery("from Products");
		ArrayList<Products> ar = new ArrayList<>();
		try {
			ar = (ArrayList<Products>) query.list();
			System.out.println("Length of Items in db :: " + ar.size());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		System.out.println(ar);
		session.close();
		return ar;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Products> fetchProducts(List<String> desiredList) {
		System.out.println("Inside load method :: ");
		// Get sessionfactory object initialized in Listener.java and create
		// session object
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Simple HQL for fetching list
		Query query = session.createQuery("from Products where productName in (:list)");
		query.setParameterList("list", desiredList);
		ArrayList<Products> ar = new ArrayList<>();
		try {
			ar = (ArrayList<Products>) query.list();
			System.out.println("Length of Items in db :: " + ar.size());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		System.out.println(ar);
		session.close();
		return ar;
	}

}
