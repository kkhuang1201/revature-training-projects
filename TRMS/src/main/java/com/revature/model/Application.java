package com.revature.model;

import java.sql.Date;
import java.sql.Time;

public class Application {
	private int applicationId;
	private int applicantId;
	private Date startingDate;
	private Date endingDate;
	private String location;
	private String description;
	private double cost;
	private Date submitedDate;
	private Time submitedTime;
	private int status;
	
	
	
	public Application() {
		super();
		
	}



	public Application(int applicationId, int applicantId, Date startingDate, Date endingDate, String location,
			String description, double cost, Date submitedDate, Time submitedTime, int status) {
		super();
		this.applicationId = applicationId;
		this.applicantId = applicantId;
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



	public int getApplicantId() {
		return applicantId;
	}



	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
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
		return "Application [applicationId=" + applicationId + ", applicantId=" + applicantId + ", startingDate="
				+ startingDate + ", endingDate=" + endingDate + ", location=" + location + ", description="
				+ description + ", cost=" + cost + ", submitedDate=" + submitedDate + ", submitedTime=" + submitedTime
				+ ", status=" + status + "]";
	}
	
	
	
	
	
}
