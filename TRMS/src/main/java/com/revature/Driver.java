package com.revature;

import java.sql.Date;
import java.sql.Time;

import com.revature.model.Application;
import com.revature.model.Employee;
import com.revature.service.ApplicationService;
import com.revature.service.EmployeeService;

public class Driver {
	public static void main(String...args) {
		//Employee(int supervisorId, int type, String firstName, String lastName, String username,
		//	String password, String email, String phoneNumber, String street, String city, String state);
		EmployeeService employeeService = new EmployeeService();
		ApplicationService applicationService = new ApplicationService();
//		Employee newEmployee = new Employee(employeeService.getEmployeeById(4), 2, "admin", "admin", "admin123",
//			"password", "admin@gmail.com", "9163478889", "Fun street", "Brooklyn", "NY"); 
//	
		//employeeService.add(newEmployee);
		//Employee e = employeeService.getEmployeeById(1);
		//System.out.println(e);
		
		//employeeService.delete(newEmployee);
		
		//System.out.println(employeeService.getAllEmployees());
		//System.out.println(employeeService.getEmployeeById(1));
		
//		Application(int applicantId, Date startingDate, Date endingDate, String location,
//				String description, double cost, Date submitedDate, Time submitedTime, int status)
		
//		Application application = new Application(1,2,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),"NY"
//				,"YES",5000.0,new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),1);
		
		//applicationService.add(application);
		//applicationService.update(application);
//		System.out.println(applicationService.getAllApplications());
//		Application application = applicationService.getApplicationById(1);
//		applicationService.delete(application);
	

		System.out.println(applicationService.getAllByApplicant(7));
		
	}
	
	
}
