package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rewardsTable")
public class EmployeeRewardsRecognition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rewardID;
	private String recognitionType;
	private String description;
	@ManyToOne
	@JoinColumn(name = "empID",nullable = false)
	private Employee employee;
	public Integer getRewardID() {
		return rewardID;
	}
	public void setRewardID(Integer rewardID) {
		this.rewardID = rewardID;
	}
	public String getRecognitionType() {
		return recognitionType;
	}
	public void setRecognitionType(String recognitionType) {
		this.recognitionType = recognitionType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
