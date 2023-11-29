package com.employee.bean;

public class RewardsReq {
	
	private String recognitionType;
	private String description;
	private Integer empID;
	private Integer hrEmpID;
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
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	public Integer getHrEmpID() {
		return hrEmpID;
	}
	public void setHrEmpID(Integer hrEmpID) {
		this.hrEmpID = hrEmpID;
	}
	
}
