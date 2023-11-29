package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmployeeAppraisals")
public class EmployeeAppraisals {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appraisalID;
	private String appraisalDate;
	private String comments;
	private String rating;
    @ManyToOne
    @JoinColumn(name = "empID", nullable = false)
    private Employee employee;
	public Integer getAppraisalID() {
		return appraisalID;
	}
	public void setAppraisalID(Integer appraisalID) {
		this.appraisalID = appraisalID;
	}
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
