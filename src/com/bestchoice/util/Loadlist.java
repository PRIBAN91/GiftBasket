package com.bestchoice.util;

import java.util.*;
import org.hibernate.*;
import com.bestchoice.model.ProductList;

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
	public ArrayList<ProductList> loadWordList() {
		System.out.println("Inside load method :: ");
		// Get sessionfactory object initialized in Listener.java and create
		// session object
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Simple HQL for fetching list
		Query query = session.createQuery("from ProductList");
		ArrayList<ProductList> ar = new ArrayList<>();
		try {
			ar = (ArrayList<ProductList>) query.list();
			System.out.println("Length of Items in db :: " + ar.size());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		System.out.println(ar);
		session.close();
		return ar;
	}

}
