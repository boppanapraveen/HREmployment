package com.employee.bean;

public class ReviewRequest {
	private String reviewDate;
	private String feedback;
	private Integer performanceRating;
	private Integer empID;
	private Integer hrEmpID;
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Integer getPerformanceRating() {
		return performanceRating;
	}
	public void setPerformanceRating(Integer performanceRating) {
		this.performanceRating = performanceRating;
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
