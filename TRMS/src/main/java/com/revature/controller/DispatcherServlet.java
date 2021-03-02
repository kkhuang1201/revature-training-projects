package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
import com.revature.model.Application;
import com.revature.model.Employee;
import com.revature.service.ApplicationService;
import com.revature.service.EmployeeService;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getFormatterLogger(DispatcherServlet.class);
	private static EmployeeService employeeService = new EmployeeService();
	private static ApplicationService applicationService = new ApplicationService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI();
		System.out.println(URI);
		String[] tokens = URI.split("/");
		final String RESOURCE = tokens[tokens.length - 1];

		switch (RESOURCE) {
		case "login":
			login(req, resp);
			break;
		case "loadHomePage":
			loadHomePage(req, resp);
			break;
		case "register":
			register(req, resp);
			break;
		case "updateProfile":
			updateProfile(req, resp);
			break;
		case "getEmployees":
			getEmployees(req, resp);
			break;
		case "submitNew":
			submitNew(req, resp);
			break;
		case "getMyApplications":
			getMyReimbursements(req, resp);
		case "getPendingApplications":
			getPendingReimbursements(req,resp);
		case "getAllApplications":
			getAllReimbursements(req,resp);
			break;
		default:
			manageReimbursements(req,resp,RESOURCE);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	// Authenticate user by it's username and password
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		if (employeeService.isValid(username, password)) {
			session.setAttribute("myUsername", username);
			// RequestDispatcher dispatcher = req.getRequestDispatcher("/Pages/home.html");
			// dispatcher.forward(req, resp);
			resp.sendRedirect("/TRMS/Pages/home.html");
		} else {
			session.invalidate();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/Pages/index.html");
			dispatcher.forward(req, resp);
			// resp.sendRedirect("/TRMS/Pages/index.html");
		}

	}

	// load home page for user if it is successfully authenticated
	protected void loadHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I am inside of loadHomePage Servlet!");
		HttpSession session = req.getSession();
		PrintWriter write = resp.getWriter();

		String username = (String) session.getAttribute("myUsername");
		System.out.println(username);
		Employee employee = employeeService.getEmployeeByUsername(username);
		System.out.println(employee);

		ObjectMapper objectMapper = new ObjectMapper();
		final String JSON = objectMapper.writeValueAsString(employee);
		write.write(JSON);
		System.out.println(JSON);

	}

	// Register a new employee account
	protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I am inside of register servlet");
		// Employee(int empId, Employee supervisor, int type, String firstName, String
		// lastName, String username,
		// String password, String email, String phoneNumber, String street, String
		// city, String state)

		HttpSession session = req.getSession();

		// Get a manager by it's username
		String managerUsername = (String) session.getAttribute("myUsername");
		Employee manager = employeeService.getEmployeeByUsername(managerUsername);

		// Get data from register form
		String firstName = req.getParameter("firstName").toUpperCase();
		String lastName = req.getParameter("lastName").toUpperCase();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String state = req.getParameter("state");

		// Create a employee
		Employee newEmployee = new Employee(manager, 2, firstName, lastName, username, password, email, phoneNumber,
				street, city, state);

		// Add the newEmployee into database
		employeeService.add(newEmployee);
		System.out.println("Successully add a new employee");
		resp.sendRedirect("/TRMS/Pages/register_successfully.html");
	}

	// Update employee's profile
	protected void updateProfile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("I am inside of update servlet");
		// Employee(int empId, Employee supervisor, int type, String firstName, String
		// lastName, String username,
		// String password, String email, String phoneNumber, String street, String
		// city, String state)

		HttpSession session = req.getSession();

		// Get a manager by it's username
		String employeeUsername = (String) session.getAttribute("myUsername");
		System.out.println(employeeUsername);
		Employee employee = employeeService.getEmployeeByUsername(employeeUsername);

		// Get data from update form
		String firstName = req.getParameter("firstName").toUpperCase();
		String lastName = req.getParameter("lastName").toUpperCase();
		// String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String state = req.getParameter("state");

		// Use set method to modify employee's profile
		System.out.println(firstName);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setPassword(password);
		employee.setEmail(email);
		employee.setPhoneNumber(phoneNumber);
		employee.setStreet(street);
		employee.setCity(city);
		employee.setState(state);

		// Update employee's profile in database
		System.out.println("Can I up date?" + employee);
		employeeService.update(employee);
		System.out.println("Successully add a new employee");
		resp.sendRedirect("/TRMS/Pages/update_successfully.html");
	}

	// Get all employees from database
	protected void getEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I am inside of getEmployees Servlet!");
		HttpSession session = req.getSession();
		PrintWriter write = resp.getWriter();

		String username = (String) session.getAttribute("myUsername");
		System.out.println(username);

		List<Employee> employees = employeeService.getAllEmployees();
		System.out.println(employees);
		ObjectMapper objectMapper = new ObjectMapper();
		final String JSON = objectMapper.writeValueAsString(employees);
		write.write(JSON);
		System.out.println(JSON);
	}

	// Submit a new reimbursement form
	protected void submitNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I am inside of submit rembursement servlet");
		// Employee(int empId, Employee supervisor, int type, String firstName, String
		// lastName, String username,
		// String password, String email, String phoneNumber, String street, String
		// city, String state)

		// public Application(Employee applicant, Date startingDate, Date endingDate,
		// String location,
		// String description, double cost, Date submitedDate, Time submitedTime, int
		// status)

		HttpSession session = req.getSession();

		// Get an applicant by it's username
		String applicantUsername = (String) session.getAttribute("myUsername");
		Employee applicant = employeeService.getEmployeeByUsername(applicantUsername);

		// Get data from reimbursement form
		java.util.Date startDate = null;
		java.util.Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse((req.getParameter("startDate")));
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse((req.getParameter("endDate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Date sDate = new Date(startDate.getTime());
		Date eDate = new Date(endDate.getTime());
		String location = req.getParameter("location");
		String description = req.getParameter("description");
		String c = req.getParameter("cost");
		System.out.println("This is cose" + c);
		double cost = Double.parseDouble(c);

		// Get current systime for submit time
		Date submitDate = new Date(System.currentTimeMillis());
		Time submitTime = new Time(System.currentTimeMillis());

		// 1 represent "Pendding status"
		int status = 1;

		// Create an Application
		Application newApplication = new Application(applicant, sDate, eDate, location, description, cost, submitDate,
				submitTime, status);

		// Add the newApplication into database
		applicationService.add(newApplication);
		System.out.println("Successully add a new application");
		resp.sendRedirect("/TRMS/Pages/register_successfully.html");
	}

	// Get all my reimbursement applications from database
	protected void getMyReimbursements(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("I am inside of getMyReiBursement Servlet!");
		HttpSession session = req.getSession();
		PrintWriter write = resp.getWriter();

		String username = (String) session.getAttribute("myUsername");
		System.out.println(username);
		List<Application> myApplications = applicationService.getAllByApplicant(username);
		System.out.println(myApplications);
		ObjectMapper objectMapper = new ObjectMapper();
		final String JSON = objectMapper.writeValueAsString(myApplications);
		write.write(JSON);
		System.out.println(JSON);
		write.close();
	}
	
	// Get pending reimbursement applications from database
			protected void getPendingReimbursements(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				System.out.println("I am inside of getPendingReiBursement Servlet!");
				HttpSession session = req.getSession();
				PrintWriter write = resp.getWriter();

				String username = (String) session.getAttribute("myUsername");
				System.out.println(username);
				List<Application> allApplications = applicationService.getAllPendindByManager(username);
				System.out.println(allApplications);
				ObjectMapper objectMapper = new ObjectMapper();
				final String JSON = objectMapper.writeValueAsString(allApplications);
				write.write(JSON);
				System.out.println(JSON);
				write.close();
			}
	
	// Get all reimbursement applications from database
		protected void getAllReimbursements(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			System.out.println("I am inside of getAllReiBursement Servlet!");
			HttpSession session = req.getSession();
			PrintWriter write = resp.getWriter();

			String username = (String) session.getAttribute("myUsername");
			System.out.println(username);
			List<Application> allApplications = applicationService.getAllResolved();
			System.out.println(allApplications);
			ObjectMapper objectMapper = new ObjectMapper();
			final String JSON = objectMapper.writeValueAsString(allApplications);
			write.write(JSON);
			System.out.println(JSON);
			write.close();
		}
		
		//update reimbursement's status after the manager made decision
		protected void manageReimbursements(HttpServletRequest req, HttpServletResponse resp,String resouce)
				throws ServletException, IOException {
			System.out.println("I am inside of manageReiBursement Servlet!");
			HttpSession session = req.getSession();
			System.out.print(resouce);
			
			String[] tokens = resouce.split("-");
			System.out.println(tokens[0]);
			System.out.println(tokens[1]);
			int appId = Integer.parseInt(tokens[1]);
			String status = tokens[0];
			
			Application application = applicationService.getApplicationById(appId);
			if(application != null) {
				if(status.equals("Accept")) {
					application.setStatus(2);
					applicationService.update(application);
					resp.sendRedirect("/TRMS/Pages/register_successfully.html");
				}
				if(status.equals("Reject")) {
					application.setStatus(3);
					applicationService.update(application);
					resp.sendRedirect("/TRMS/Pages/register_successfully.html");
				}
			}else{
				resp.sendRedirect("/TRMS/Pages/home.html");
			}
			
			
		}

}
