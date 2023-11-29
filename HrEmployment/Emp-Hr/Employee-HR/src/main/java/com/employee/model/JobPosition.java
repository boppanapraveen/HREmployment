package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "JobPosition")
public class JobPosition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobPositionID;
	private String jobTitle;
	private String description;
	private String salaryRange;
	public Integer getJobPositionID() {
		return jobPositionID;
	}
	public void setJobPositionID(Integer jobPositionID) {
		this.jobPositionID = jobPositionID;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSalaryRange() {
		return salaryRange;
	}
	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}
	
	

}
