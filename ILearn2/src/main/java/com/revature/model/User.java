package com.revature.model;

import java.util.Set;

import javax.persistence.*;




/**
 * 
 * @author kenny Huang
 *
 */
@Entity
@Table(name = "users")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int userid;
	@Column
	private String email;
	@Column
	private String user_password;
	@Column
	private String first_name;
	@Column
	private String last_name;
	
	/*
	 * We want to create a many-to-many relationship between users and courses 
	 * as a user can enroll many courses and a course can be enrolled by many users
	 * This annotation specifies that we want Hibernate to create a join table between these entities when we run the "update" mode on Hibernate the first time.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = {@JoinColumn(name = "userid")},
	inverseJoinColumns = {@JoinColumn(name = "courseid")})
	private Set<Course> enrolled_courses;
	
	public User() {
		super();
	}

	public User(int userId, String email, String user_password, String first_name, String last_name,
			Set<Course> enrolled_courses) {
		super();
		this.userid = userId;
		this.email = email;
		this.user_password = user_password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.enrolled_courses = enrolled_courses;
	}

	//Override the constructor without userId parameter since we use serial type in our table
	public User(String email, String user_password, String first_name, String last_name,
			Set<Course> enrolled_courses) {
		super();
		this.email = email;
		this.user_password = user_password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.enrolled_courses = enrolled_courses;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Set<Course> getEnrolled_courses() {
		return enrolled_courses;
	}

	public void setEnrolled_courses(Set<Course> enrolled_courses) {
		this.enrolled_courses = enrolled_courses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enrolled_courses == null) ? 0 : enrolled_courses.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((user_password == null) ? 0 : user_password.hashCode());
		result = prime * result + userid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enrolled_courses == null) {
			if (other.enrolled_courses != null)
				return false;
		} else if (!enrolled_courses.equals(other.enrolled_courses))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (user_password == null) {
			if (other.user_password != null)
				return false;
		} else if (!user_password.equals(other.user_password))
			return false;
		if (userid != other.userid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", email=" + email + ", user_password=" + user_password + ", first_name="
				+ first_name + ", last_name=" + last_name + ", enrolled_courses=" + enrolled_courses + "]";
	}

	

	
	
	
	
	
}
