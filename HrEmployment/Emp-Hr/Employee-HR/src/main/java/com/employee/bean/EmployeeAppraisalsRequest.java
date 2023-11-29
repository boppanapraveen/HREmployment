package com.employee.bean;

public class EmployeeAppraisalsRequest {
	private String appraisalDate;
	private String comments;
	private String rating;
	private Integer empID;
	private Integer hrEmpID;
	public String getAppraisalDate() {
		return appraisalDate;
	}
	public void setAppraisalDate(String appraisalDate) {
		this.appraisalDate = appraisalDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
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
