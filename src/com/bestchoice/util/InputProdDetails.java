package com.bestchoice.util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bestchoice.model.Products;

public class InputProdDetails {

	public void insertProductData(String prodName, String subProdName, double price) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Products prod = new Products();
			prod.setProductName(prodName);
			prod.setSubProductName(subProdName);
			prod.setPrice(price);
			prod.setReviewValue(10.0);
			prod.setReviewTotal(10.0);
			prod.setReviewCount(1);
			session.save(prod);
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println("Error in updating customer rating.");
			e.printStackTrace();
			throw e;
		}
	}
}
