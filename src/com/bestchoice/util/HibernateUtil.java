package com.bestchoice.util;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import com.bestchoice.model.Products;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory buildSessionFactory() {
		try {

			// Loading configuration from Hibernate.properties file
			Configuration configuration = new Configuration();

			Properties properties = new Properties();

			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Hibernate.properties"));

			configuration.setProperties(properties);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			configuration.addAnnotatedClass(Products.class);

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		sessionFactory.close();
	}

}