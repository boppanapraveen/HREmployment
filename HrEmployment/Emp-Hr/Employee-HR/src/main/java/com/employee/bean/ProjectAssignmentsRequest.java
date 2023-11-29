package com.employee.bean;

public class ProjectAssignmentsRequest {
	
	private String startDate;
	private String endDate;
	private String description;
	private Integer empID;
	private Integer hrEmpID;
	private Integer projectID;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public Integer getProjectID() {
		return projectID;
	}
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	
	

}
