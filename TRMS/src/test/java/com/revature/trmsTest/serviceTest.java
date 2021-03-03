package com.revature.trmsTest;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.Application;
import com.revature.model.Employee;
import com.revature.repository.ApplicationRepositoryImpl;
import com.revature.repository.EmployeeRepositoryImpl;
import com.revature.service.ApplicationService;
import com.revature.service.EmployeeService;

public class serviceTest {
	
	@InjectMocks
	private static EmployeeService employeeService;
	
	@Mock
	private EmployeeRepositoryImpl employeeRepositoryImpl;
	
	@InjectMocks
	private static ApplicationService applicationService;
	
	@Mock
	private static ApplicationRepositoryImpl applicationRepositoryImpl;
	
	@BeforeClass
	public static void setup() {
		employeeService = new EmployeeService();
		applicationService = new ApplicationService();
	}
	
	@Before
	public void beforeSetup() {
		
		MockitoAnnotations.openMocks(this);
		
		ArrayList<Employee> employees = new ArrayList<>();
		ArrayList<Application> applications = new ArrayList<>();
		
		Employee manager = new Employee(0,null, 2, "admin", "admin", "admin123",
				"password", "admin@gmail.com", "9163478889", "Fun street", "Brooklyn", "NY");
		
		Employee employee1 = new Employee(1,manager, 1, "a", "a", "a123",
				"password", "a@gmail.com", "10", "a street", "a city", "a state"); 
		Employee employee2 = new Employee(2,manager, 1, "b", "b", "b123",
				"password", "b@gmail.com", "9", "b street", "b city", "b state"); 
		Employee employee3 = new Employee(3,employee1, 1, "c", "c", "c123",
				"password", "a@gmail.com", "8", "c street", "c city", "c state"); 
		Employee employee4 = new Employee(4,employee1, 2, "f", "f", "f123",
				"password", "b@gmail.com", "7", "f street", "f city", "f state"); 
		Employee employee5 = new Employee(5,employee1, 2, "d", "d", "d123",
				"password", "a@gmail.com", "6", "d street", "d city", "d state"); 
		Employee employee6 = new Employee(6,manager, 1, "e", "e", "e123",
				"password", "b@gmail.com", "5", "e street", "e city", "e state"); 
		
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
		employees.add(employee4);
		employees.add(employee5);
		employees.add(employee6);
		
		Application application1 = new Application(1,employee1,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),"NY"
				,"a",500,new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),1);
		Application application2 = new Application(2,employee1,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),"NY"
				,"b",400,new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),1);
		Application application3 = new Application(3,employee2,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),"NY"
				,"c",300,new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),1);
		Application application4 = new Application(4,employee2,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),"NY"
				,"d",200,new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),1);
		Application application5 = new Application(5,employee3,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),"NY"
				,"e",100,new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),1);
		
		
		applications.add(application1);
		applications.add(application2);
		applications.add(application3);
		applications.add(application4);
		applications.add(application5);
		
		Mockito.when(employeeRepositoryImpl.getAll()).thenReturn(employees);
		Mockito.when(employeeRepositoryImpl.getEmployee(6)).thenReturn(employee6);
		Mockito.when(employeeRepositoryImpl.getEmployee("f123")).thenReturn(employee4);

		
		Mockito.when(applicationRepositoryImpl.findAll()).thenReturn(applications);
				
		
	}
	
	@Test
	public void testGetAllEmployees() {
		
		List<Employee> employees = employeeService.getAllEmployees();
		int i = 1;
		for(Employee e: employees) {
			Assert.assertEquals(i++, e.getEmpId());
		}	
	}
	
	@Test
	public void testGetAllManagers(){
		
		List<Employee> manages = employeeService.getAllManagers();
		
		for(Employee m: manages) {
			Assert.assertEquals(1, m.getType());
		}
	}
	
	@Test
	public void testIsValid() {
		
		boolean valid = employeeService.isValid("f123", "password");
		boolean inValid = employeeService.isValid("f123", "WrongPass");
		boolean inValid1 = employeeService.isValid("wrongUsername", "password");
		Assert.assertEquals(true, valid);
		Assert.assertEquals(false, inValid);
		Assert.assertEquals(false, inValid1);
		
	}
	
	@Test
	public void testGetManager() {
		Employee manager = employeeService.getManager(6);
		Assert.assertEquals(6,manager.getEmpId());
	}
	
	@Test
	public void testGetEmployeeByUsername() {
		Employee employee = employeeService.getEmployeeByUsername("f123");
		
		Assert.assertEquals("f123", employee.getUserName());
		
	}
	
	
	@Test
	public void testGetAllEmplHSM() {
		List<Employee> employees = employeeService.getAllEmplHSM(6);
		for(Employee e: employees) {
			Assert.assertEquals(6, e.getSupervisor().getEmpId());
		}
		
	}
	
	@Test
	public void testGetAllApplications() {
		List<Application> applications = applicationService.getAllApplications();
		int i=1;
		for(Application a : applications) {
			Assert.assertEquals(i++, a.getApplicationId());
		}
	}
	
	@Test
	public void testGetAllByApplicant() {
		List<Application> applications = applicationService.getAllByApplicant(2);
		for(Application a: applications) {
			Assert.assertEquals(2, a.getApplicant().getEmpId());
		}
	}
	
	@Test
	public void testGetAllByApplicantUsername() {
		List<Application> applications = applicationService.getAllByApplicant("a123");
		for(Application a: applications) {
			Assert.assertEquals("a123", a.getApplicant().getUserName());
		}
	}
	
	@Test
	public void testGetAllPendingByManager() {
		List<Application> applications = applicationService.getAllPendindByManager("a123");
		for(Application a: applications) {
			Assert.assertEquals(1, a.getStatus());
			Assert.assertEquals(1,a.getApplicant().getSupervisor().getEmpId());
		}
	}
	
	@Test
	public void testGetAllResolved() {
		List<Application> applications = applicationService.getAllResolved();
		for(Application a: applications) {
			Assert.assertNotEquals(1, a.getStatus());
		}
	}
	
	
	
	
}
