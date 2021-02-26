package com.revature.repository;

import java.util.List;

import com.revature.model.Application;

/**
 *  This interface consists methods that we will need to implement in the class 
 * that wants to access the database to retrieve,add,update,delete application.  
 * @author kenny Huang
 *
 */
public interface ApplicationRepository {
	
	//insert new application into the database
	void add(Application application);
	//update existed application from the database
	void update(Application appliation);
	//remove existed application from the database
	void delete(Application application);
	//Retrieve all applications from the database
	List<Application> findAll();
	//get a existed application by its Id
	Application getApplication(int id);
}
