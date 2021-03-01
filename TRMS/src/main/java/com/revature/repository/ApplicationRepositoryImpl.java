package com.revature.repository;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Application;
import com.revature.util.HibernateSessionFactory;

public class ApplicationRepositoryImpl implements ApplicationRepository{
	
	
	@Override
	public void add(Application application) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.save(application);
			tx.commit();
		}catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			s.close();
		}	
	}

	@Override
	public void update(Application appliation) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.update(appliation);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		
	}

	@Override
	public void delete(Application application) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.delete(application);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			s.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> findAll() {
		List<Application> applications = new ArrayList<>();
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			applications = s.createQuery("From Application").getResultList();
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		return applications;
	}

	@Override
	public Application getApplication(int id) {
		Application application = null;
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			application = s.get(Application.class,id);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			;
		}
		return application;
	}
	
	
}
