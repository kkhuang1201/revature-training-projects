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



	public Employee getApplicant() {
		return applicant;
	}



	public void setApplicant(Employee applicant) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicant == null) ? 0 : applicant.hashCode());
		result = prime * result + applicationId;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endingDate == null) ? 0 : endingDate.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
		result = prime * result + status;
		result = prime * result + ((submitedDate == null) ? 0 : submitedDate.hashCode());
		result = prime * result + ((submitedTime == null) ? 0 : submitedTime.hashCode());
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
		Application other = (Application) obj;
		if (applicant == null) {
			if (other.applicant != null)
				return false;
		} else if (!applicant.equals(other.applicant))
			return false;
		if (applicationId != other.applicationId)
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endingDate == null) {
			if (other.endingDate != null)
				return false;
		} else if (!endingDate.equals(other.endingDate))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (startingDate == null) {
			if (other.startingDate != null)
				return false;
		} else if (!startingDate.equals(other.startingDate))
			return false;
		if (status != other.status)
			return false;
		if (submitedDate == null) {
			if (other.submitedDate != null)
				return false;
		} else if (!submitedDate.equals(other.submitedDate))
			return false;
		if (submitedTime == null) {
			if (other.submitedTime != null)
				return false;
		} else if (!submitedTime.equals(other.submitedTime))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", applicant=" + applicant + ", startingDate="
				+ startingDate + ", endingDate=" + endingDate + ", location=" + location + ", description="
				+ description + ", cost=" + cost + ", submitedDate=" + submitedDate + ", submitedTime=" + submitedTime
				+ ", status=" + status + "]";
	}



	
	
	
	
	
}
