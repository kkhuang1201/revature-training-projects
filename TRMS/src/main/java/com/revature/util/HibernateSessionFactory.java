package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
	/**
	 * This is a utility class for building a SessionFactory and returning Hibernate Sessions.
	 * Note that our SessionFactory will be a singleton as SessionFactory(ies) are expensive build 
	 * and we don't need more than one SessionFactory to retrieve sessions in the application 
	 */
	
	private static SessionFactory sessionFactory;
	
	/**
	 * This method is used to get SessionFactory's current session 
	 * @return Session This returns single SessionFactory's current session instance 
	 */
	public static Session getSession() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure()
						.setProperty("hibernate.connection.url", System.getenv("dburl"))
						.setProperty("hibernate.connection.username",System.getenv("dbusername"))
						.setProperty("hibernate.connection.password",System.getenv("dbpassword"))
						.buildSessionFactory();
			
			
		}
		
		return sessionFactory.getCurrentSession();
	}

}
