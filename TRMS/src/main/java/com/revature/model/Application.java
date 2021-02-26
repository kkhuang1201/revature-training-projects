package com.revature.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name = "applications")
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicationId")
	private int applicationId;
	
	@JoinColumn(name = "applicantId")
	@ManyToOne
	private Employee applicant;
	
	@Column(name = "startDate")
	private Date startingDate;
	
	@Column(name = "endDate")
	private Date endingDate;
	
	@Column(name = "event_location")
	private String location;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "event_cost")
	private double cost;
	
	@Column(name = "submit_date")
	private Date submitedDate;
	
	@Column(name = "submit_time")
	private Time submitedTime;
	
	@Column(name = "status")
	private int status;
	
	
	
	public Application() {
		super();
		
	}



	public Application(int applicationId, Employee applicant, Date startingDate, Date endingDate, String location,
			String description, double cost, Date submitedDate, Time submitedTime, int status) {
		super();
		this.applicationId = applicationId;
		this.applicant = applicant;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.submitedDate = submitedDate;
		this.submitedTime = submitedTime;
		this.status = status;
	}
	
	public Application(Employee applicant, Date startingDate, Date endingDate, String location,
			String description, double cost, Date submitedDate, Time submitedTime, int status) {
		super();
		this.applicant = applicant;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.submitedDate = submitedDate;
		this.submitedTime = submitedTime;
		this.status = status;
	}



	public int getApplicationId() {
		return applicationId;
	}



	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}



	public Employee getApplicantId() {
		return applicant;
	}



	public void setApplicantId(Employee applicant) {
		this.applicant = applicant;
	}



	public Date getStartingDate() {
		return startingDate;
	}



	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}



	public Date getEndingDate() {
		return endingDate;
	}



	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getCost() {
		return cost;
	}



	public void setCost(double cost) {
		this.cost = cost;
	}



	public Date getSubmitedDate() {
		return submitedDate;
	}



	public void setSubmitedDate(Date submitedDate) {
		this.submitedDate = submitedDate;
	}



	public Time getSubmitedTime() {
		return submitedTime;
	}



	public void setSubmitedTime(Time submitedTime) {
		this.submitedTime = submitedTime;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", applicant=" + applicant + ", startingDate="
				+ startingDate + ", endingDate=" + endingDate + ", location=" + location + ", description="
				+ description + ", cost=" + cost + ", submitedDate=" + submitedDate + ", submitedTime=" + submitedTime
				+ ", status=" + status + "]";
	}



	
	
	
	
	
}
