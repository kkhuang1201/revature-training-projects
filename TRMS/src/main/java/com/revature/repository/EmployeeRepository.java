package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

/**
 * This interface consists methods that we will need to implement in the class 
 * that wants to access the database to retrieve,add,update,delete employees.  
 * @author kenny
 *
 */
public interface EmployeeRepository{
	
	//insert new employee into the database
	void add(Employee employee);
	//update existed employee from the database
	void update(Employee emoloyee);
	//remorve existed employee from the database
	void delete(Employee employee);
	//find an employee by its id number
	Employee getEmployee(int id);
	//find an employee by its username
	Employee getEmployee(String username);
	//find all existed employees from the database
	List<Employee> getAll();

}
