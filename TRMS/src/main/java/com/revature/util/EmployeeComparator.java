package com.revature.util;

import java.util.Comparator;

import com.revature.model.Employee;

public class EmployeeComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return o1.getEmpId() - o2.getEmpId();
	}
	
}
