package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.EmployeeRepositoryImpl;

/**
 * This is the service layer class that we will use to provide logic operate on 
 * the data sent to and from the repository and the client.
 * @author kenny Huang
 *
 */
public class EmployeeService {
	
	private static final Logger LOG = LogManager.getFormatterLogger(EmployeeService.class);
	private EmployeeRepository employeeRepository;
	
	public EmployeeService() {
		employeeRepository = new EmployeeRepositoryImpl();	//Creates instance of EmployeeRepository to call it's methods  
	}
	
	public void add(Employee employee) {
		this.employeeRepository.add(employee);
	}
	
	public void update(Employee employee) {
		this.employeeRepository.update(employee);
	}
	
	public void delete(Employee employee) {
		if(employee!=null) {
			this.employeeRepository.delete(employee);
		}
	}
	
	public Employee getEmployeeById(int id) {
		return this.employeeRepository.getEmployee(id);
	}
	
	public Employee getEmployeeByUsername(String username) {
		return this.employeeRepository.getEmployee(username);
		
	}
	
	public List<Employee> getAllEmployees(){
		
		LOG.info("Find all employees");
		return this.employeeRepository.getAll();
	}
	
	public boolean isValid(String username, String password) {
		Employee employee = this.getEmployeeByUsername(username);
		if(employee != null && employee.getPassword().equals(password)) {
			return true;
		}else {
			return false;
		}
	}
	
}
