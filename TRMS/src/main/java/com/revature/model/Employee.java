package com.revature.model;

public class Employee {
	private int empId;
	private int supervisorId;
	private int type;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String phoneNumber;
	private String street;
	private String city;
	private String state;
	
	public Employee() {
		super();
	
	}

	public Employee(int empId, int supervisorId, int type, String firstName, String lastName, String userName,
			String password, String email, String phoneNumber, String street, String city, String state) {
		super();
		this.empId = empId;
		this.supervisorId = supervisorId;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.city = city;
		this.state = state;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", supervisorId=" + supervisorId + ", type=" + type + ", firstName="
				+ firstName + ", lastName=" + lastName + ", userName=" + userName + ", password=" + password
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", street=" + street + ", city=" + city
				+ ", state=" + state + "]";
	}
	
	

}