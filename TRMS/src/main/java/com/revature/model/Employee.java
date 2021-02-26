package com.revature.model;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empId")
	private int empId;
	
	@JoinColumn(name = "supervisorId")
	@ManyToOne
	private Employee supervisor;
	
	@Column(name = "emp_type")
	private int type;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "emp_password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phoneNum")
	private String phoneNumber;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	public Employee() {
		super();
	
	}

	
	public Employee(int empId, Employee supervisor, int type, String firstName, String lastName, String username,
			String password, String email, String phoneNumber, String street, String city, String state) {
		super();
		this.empId = empId;
		this.supervisor = supervisor;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.city = city;
		this.state = state;
	}




	public Employee(Employee supervisor, int type, String firstName, String lastName, String username,
			String password, String email, String phoneNumber, String street, String city, String state) {
		super();
		this.supervisor = supervisor;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
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

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisorId(Employee supervisor) {
		this.supervisor = supervisor;
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
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
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
		return "Employee [empId=" + empId + ", supervisor=" + supervisor + ", type=" + type + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", street=" + street + ", city=" + city + ", state=" + state + "]";
	}

	
	

}