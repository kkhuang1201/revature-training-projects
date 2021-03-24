package com.revature.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.revature.model.User;

@Component
@Order(1)
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpSession session = req.getSession(false);
//		if(session == null && req.getRequestURI().equals("/iLearn/login")) {
//			chain.doFilter(request, response);
//		} else if(session != null && (User) session.getAttribute("user") != null) {
			chain.doFilter(request, response);
//		} 
		
	}

}
