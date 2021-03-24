package com.revature.model;

import java.sql.Date;

import javax.persistence.*;


/**
 * 
 * @author kenny Huang
 *
 */
@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int courseid;
	
	@Column
	private String title;
	
	@JoinColumn(name = "creator")
	@ManyToOne()
	private User creator;
	
	@Column
	private String description;
	
	@Column
	private boolean public_access;
	
	@Column
	private String access_code;
	
	@Column
	private Date date_created;
	
	@Column
	private int number_enrolled;

	public Course() {
		super();
	
	}
	



	public Course(int courseId, String title, User creator, String description, boolean public_access,
			String access_code, Date date_created, int number_enrolled) {
		super();
		this.courseid = courseId;
		this.title = title;
		this.creator = creator;
		this.description = description;
		this.public_access = public_access;
		this.access_code = access_code;
		this.date_created = date_created;
		this.number_enrolled = number_enrolled;
	}




	//Override the constructor without courseId parameter since we use serial type in our table
	public Course(String title, User creator, String description, boolean public_access,
			String access_code, Date date_created, int number_enrolled) {
		super();
		this.title = title;
		this.creator = creator;
		this.description = description;
		this.public_access = public_access;
		this.access_code = access_code;
		this.date_created = date_created;
		this.number_enrolled = number_enrolled;
	}




	public int getCourseid() {
		return courseid;
	}




	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public User getCreator() {
		return creator;
	}




	public void setCreator(User creator) {
		this.creator = creator;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public boolean isPublic_access() {
		return public_access;
	}




	public void setPublic_access(boolean public_access) {
		this.public_access = public_access;
	}




	public String getAccess_code() {
		return access_code;
	}




	public void setAccess_code(String access_code) {
		this.access_code = access_code;
	}




	public Date getDate_created() {
		return date_created;
	}




	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}




	public int getNumber_enrolled() {
		return number_enrolled;
	}




	public void setNumber_enrolled(int number_enrolled) {
		this.number_enrolled = number_enrolled;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access_code == null) ? 0 : access_code.hashCode());
		result = prime * result + courseid;
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((date_created == null) ? 0 : date_created.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + number_enrolled;
		result = prime * result + (public_access ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Course other = (Course) obj;
		if (access_code == null) {
			if (other.access_code != null)
				return false;
		} else if (!access_code.equals(other.access_code))
			return false;
		if (courseid != other.courseid)
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (date_created == null) {
			if (other.date_created != null)
				return false;
		} else if (!date_created.equals(other.date_created))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (number_enrolled != other.number_enrolled)
			return false;
		if (public_access != other.public_access)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Course [courseid=" + courseid + ", title=" + title + ", creator=" + creator + ", description="
				+ description + ", public_access=" + public_access + ", access_code=" + access_code + ", date_created="
				+ date_created + ", number_enrolled=" + number_enrolled + "]";
	}




	


	
	

}
