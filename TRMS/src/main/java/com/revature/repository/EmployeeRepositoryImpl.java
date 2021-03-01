package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Employee;
import com.revature.util.HibernateSessionFactory;



/**
 * This is the class that implements EmployeeReposotpry interface 
 * This class will override all the methods that defined in the interface 
 * and define our actual method implementation. 
 * @author kenny Huang
 *
 */
public class EmployeeRepositoryImpl implements EmployeeRepository{

	@Override
	public void add(Employee employee) {
		
		
		//All of the work is done with the context of a Hibernate session
		Session s =null;
		
		/*
		 * The Transaction interface allows us control over our DB transactions.
		 * we can use it to rollback changes, commit changes, and begin transactions. 
		 */
		Transaction tx = null;
		
		//Handle HibernateException by using try catch block
		try {
			s = HibernateSessionFactory.getSession(); //Get a Hibernate Session
			tx = s.beginTransaction(); //Call this method to start our transaction
			s.save(employee); //This method creates a new employee record in the Employee table
			tx.commit(); //Commit our transaction
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();    //When Exception occurs, rollback transaction
		}finally {
			s.close(); // close Hibernate session
		}
		
	}

	@Override
	public void update(Employee employee) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.update(employee);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void delete(Employee employee) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.delete(employee);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
	}

	@Override
	public Employee getEmployee(int id) {
		Employee employee = null;
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			employee = s.get(Employee.class, id);
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			s.close();
		}
				
		return employee;
	}

	@Override
	public Employee getEmployee(String username) {
		List<Employee> employees = null;
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			String hql = "From Employee E where E.username =:username";
			TypedQuery<Employee> query = s.createQuery(hql);
			
			query.setParameter("username", username);
			employees = query.getResultList();
		
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		if(!employees.isEmpty()) {
			return employees.get(0);
		}else {
			return null;
		}
		
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAll() {
		List<Employee> employeeList = new ArrayList<>();
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			employeeList = s.createQuery("from Employee").getResultList();
			tx.commit();
		}catch(HibernateException e) {
		 e.printStackTrace();
		 tx.rollback();
		}finally {
			s.close();
		}
		return employeeList;
	}

	
}
