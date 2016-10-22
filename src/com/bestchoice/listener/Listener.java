package com.bestchoice.listener;

import java.util.*;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import com.bestchoice.util.HibernateUtil;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener,
		HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener, HttpSessionIdListener,
		ServletRequestListener, ServletRequestAttributeListener, AsyncListener {

	private Timer timer;

	/**
	 * Default constructor.
	 */
	public Listener() {

	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {

	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent arg0) {

	}

	/**
	 * @see AsyncListener#onError(AsyncEvent)
	 */
	public void onError(AsyncEvent arg0) throws java.io.IOException {

	}

	/**
	 * @see HttpSessionIdListener#sessionIdChanged(HttpSessionEvent, String)
	 */
	public void sessionIdChanged(HttpSessionEvent arg0, String arg1) {

	}

	/**
	 * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
	 */
	public void attributeAdded(ServletRequestAttributeEvent arg0) {

	}

	/**
	 * @see AsyncListener#onTimeout(AsyncEvent)
	 */
	public void onTimeout(AsyncEvent arg0) throws java.io.IOException {

	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0) {

	}

	/**
	 * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
	 */
	public void sessionWillPassivate(HttpSessionEvent arg0) {

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 * 
	 *      This is where the processing starts. This is loaded during server
	 *      startup. It can throw OutOfMemory Error under Throwable. This means
	 *      that you have to increase Tomcat heap size.
	 * 
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Hi there, just getting started up. This may take some time!");
		try {
			HibernateUtil.buildSessionFactory();
			System.out.println("Before loading list");
			// Loadlist list = new Loadlist();
			// list.loadWordList();
			System.out.println("Succesfully loaded list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent arg0) {

	}

	/**
	 * @see AsyncListener#onComplete(AsyncEvent)
	 */
	public void onComplete(AsyncEvent arg0) throws java.io.IOException {

	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent arg0) {

	}

	/**
	 * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
	 */
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {

	}

	/**
	 * @see AsyncListener#onStartAsync(AsyncEvent)
	 */
	public void onStartAsync(AsyncEvent arg0) throws java.io.IOException {

	}

	/**
	 * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent arg0) {

	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent arg0) {

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {

	}

	/**
	 * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
	 */
	public void sessionDidActivate(HttpSessionEvent arg0) {

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 * 
	 *      This is called when the server shuts down either normally or
	 *      abnormally. This method is used to cancel the timer and shutdown the
	 *      Hibernate session factory.
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
		HibernateUtil.shutdown();
	}

	/**
	 * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
	 */
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {

	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent arg0) {

	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent arg0) {

	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent arg0) {

	}

	/**
	 * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent arg0) {

	}

}
