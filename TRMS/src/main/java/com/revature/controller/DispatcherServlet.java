package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;







public class DispatcherServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getFormatterLogger(DispatcherServlet.class);
	private static EmployeeService employeeService = new EmployeeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI();
		System.out.println(URI);
		String[] tokens = URI.split("/");
		final String RESOURCE = tokens[tokens.length-1];
		
		switch(RESOURCE) {
		case "login":	
			login(req,resp);
			break;
		case "loadHomePage":
			loadHomePage(req, resp);
			break;
		case "register":
			register(req, resp);
			break;
		default:
				
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		doGet(req, resp);
	}
	
	//Authenticate user by it's username and password
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		if(employeeService.isValid(username, password)) {	
			session.setAttribute("myUsername", username);
			//RequestDispatcher dispatcher = req.getRequestDispatcher("/Pages/home.html"); 
			//dispatcher.forward(req, resp);
			resp.sendRedirect("/TRMS/Pages/home.html");
		}else {
			session.invalidate();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/Pages/index.html"); 
			dispatcher.forward(req, resp);
			//resp.sendRedirect("/TRMS/Pages/index.html");		
		}

	}
	
	//load home page for user if it is successfully authenticated
	protected void loadHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("I am inside of loadHomePage Servlet!");
		HttpSession session = req.getSession();
		PrintWriter write = resp.getWriter();
		
		String username = (String) session.getAttribute("myUsername");
		System.out.println(username);
		//EmployeeService service = new EmployeeService();
		//Employee employee = service.getEmployeeByUsername(username);
		Employee employee = employeeService.getEmployeeByUsername(username);
		System.out.println(employee);
		
		ObjectMapper objectMapper = new ObjectMapper();
		final String JSON = objectMapper.writeValueAsString(employee);
		write.write(JSON);
		System.out.println(JSON);
		
	}
	
	//Register a new employee account
	protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	System.out.println("I am inside of register servlet");
	//Employee(int empId, Employee supervisor, int type, String firstName, String lastName, String username,
	//String password, String email, String phoneNumber, String street, String city, String state)
	
	HttpSession session = req.getSession();
	
	//Get a manager by it's username 
	String managerUsername = (String) session.getAttribute("username");
	Employee manager = employeeService.getEmployeeByUsername(managerUsername);
	
	//Get data from register form
	String firstName = req.getParameter("firstName");
	String lastName = req.getParameter("lastName");
	String username = req.getParameter("username");
	String password = req.getParameter("password");
	String email = req.getParameter("email");
	String phoneNumber = req.getParameter("phoneNumber");
	String street = req.getParameter("street");
	String city = req.getParameter("city");
	String state = req.getParameter("state");
	
	//Create a employee
	Employee newEmployee = new Employee(
									manager,
									2,
									firstName,
									lastName,
									username,
									password,
									email,
									phoneNumber,
									street,
									city,
									state
									);
	
	//Add the newEmployee into database
	employeeService.add(newEmployee);
	System.out.println("Successully add a new employee");
	resp.sendRedirect("/TRMS/Pages/register_successfully.html");
	}
	

	


	

}
