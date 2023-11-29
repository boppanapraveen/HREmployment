package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "performance_review")
public class PerformanceReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewID;
	private String reviewDate;
	private String feedback;
	private Integer performanceRating;
    @ManyToOne
    @JoinColumn(name = "empID", nullable = false)
    private Employee employee;
	public Integer getReviewID() {
		return reviewID;
	}
	public void setReviewID(Integer reviewID) {
		this.reviewID = reviewID;
	}
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    
    

}
