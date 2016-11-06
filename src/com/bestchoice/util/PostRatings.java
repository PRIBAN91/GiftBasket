package com.bestchoice.util;

import java.text.DecimalFormat;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import com.bestchoice.model.Products;

public class PostRatings {

	public void postCustomerRating(String subProductName, double rating) {
		// Get session factory object initialized in Listener.java and create
		// session object
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// Simple HQL for fetching list
			Criteria cr = session.createCriteria(Products.class)
					.add(Restrictions.eq(("subProductName"), subProductName));
			Products prod = (Products) cr.uniqueResult();
			long count = prod.getReviewCount() + 1;
			double total = prod.getReviewTotal() + rating;
			double average = total / count;
			DecimalFormat df = new DecimalFormat(".#");
			average = Double.valueOf(df.format(average));
			System.out.println(average);
			prod.setReviewCount(count);
			prod.setReviewTotal(total);
			prod.setReviewValue(average);
			Transaction tx = session.beginTransaction();
			session.merge(prod);
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println("Error in updating customer rating.");
			e.printStackTrace();
			throw e;
		}
	}

}
