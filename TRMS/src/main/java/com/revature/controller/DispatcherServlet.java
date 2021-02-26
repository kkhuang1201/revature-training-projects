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
//		case "home.html":
//			loadHomePage(req, resp);
//			break;
		default:
				
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		doGet(req, resp);
	}
	
	
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
	
		
		
		HttpSession session = req.getSession();
		if(employeeService.isValid(username, password)) {	
			session.setAttribute("username", username);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/Pages/home.html"); 
			dispatcher.forward(req, resp);
			//resp.sendRedirect("/TRMS/Pages/home.html");
			out.print("True");
		}else {
			session.invalidate();
			req.setAttribute("invalid", true);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/Pages/index.html"); 
			dispatcher.forward(req, resp);
			//resp.sendRedirect("/TRMS/Pages/index.html");
			out.print("false");
				
		}
		out.close();
	
		
		
		
		
		
	}
	
	protected void loadHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();
		System.out.println(session.getAttribute("username"));
	}
	

	


	

}
