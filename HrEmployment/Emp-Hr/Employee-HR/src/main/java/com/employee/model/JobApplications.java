package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "JobApplications")
public class JobApplications {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer applicationID;
	private String applicationDate;

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

}
