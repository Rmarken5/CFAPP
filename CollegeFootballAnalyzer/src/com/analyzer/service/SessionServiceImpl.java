package com.analyzer.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionServiceImpl {

	private static SessionFactory factory;
	private static Session session;

	public static Session getSession() {
		if (session == null) {
			try {
				factory = new Configuration().configure().buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			session = factory.openSession();
		}
		return session;
	}

}
