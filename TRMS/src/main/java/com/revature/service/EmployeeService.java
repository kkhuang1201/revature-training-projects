package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.EmployeeRepositoryImpl;
import com.revature.util.EmployeeComparator;

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
	
	//Get a manager by emplId
	public Employee getManager(int id) {
		Employee  employee = this.getEmployeeById(id);
		
		if(employee != null && employee.getType() ==1) {
			return employee;
		}else {
			return null;
		}
	}
	
	public Employee getEmployeeByUsername(String username) {
		return this.employeeRepository.getEmployee(username);
		
	}
	
	public List<Employee> getAllEmployees(){
		
		LOG.info("Find all employees");
		
		List<Employee> employees = this.employeeRepository.getAll();
		employees.sort(new EmployeeComparator());
		return employees;
	}
	
	//Verify user's account
	public boolean isValid(String username, String password) {
		Employee employee = this.getEmployeeByUsername(username);
		if(employee != null && employee.getPassword().equals(password)) {
			return true;
		}else {
			return false;
		}
	}
	
	//Get all managers from all employees
	public List<Employee> getAllManagers(){
		
		List<Employee> managers = new ArrayList<>();
		managers = this.getAllEmployees();
		managers.removeIf(e -> e.getType()!=1);
		return managers;
		
	}
	
	//Get all employees that with a same manager by manager's Id
	public List<Employee> getAllEmplHSM(int id){
		Employee manager = this.getManager(id);
		List<Employee> employees = this.getAllEmployees();

		employees.removeIf(e -> (!manager.equals(e.getSupervisor())));
		return employees;
	}
	
	//Sort employee list by manager first then follow its subordinates
	public List<Employee> GetSortedEmployees(){
		List <Employee> sortedEmployees = new ArrayList<>();
		List<Employee> managers = this.getAllManagers();
		
//		for(int i =0; i< managers.size(); i++ ) {
//			sortedEmployees.add(managers.get(i));
//			
//			for(int j = 0; j < this.getAllEmplHSM(managers.get(i).getEmpId()).size(); j++) {
//				sortedEmployees.add(null)
//			}
//		}
//		

		for(Employee m: managers) {
			sortedEmployees.add(m);
			for(Employee e: this.getAllEmplHSM(m.getEmpId())) {
				sortedEmployees.add(e);
			}
		}
		return sortedEmployees;
	}
	
}
