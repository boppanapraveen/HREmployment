package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "ProjectAssignments")
public class ProjectAssignments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer assignmentID;
	private String startDate;
	private String endDate;
	private String description;
    @ManyToOne
    @JoinColumn(name = "empID", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "projectID", nullable = false)
    private ProjectDetails projectDetails;
    
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}
	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}
	public Integer getAssignmentID() {
		return assignmentID;
	}
	public void setAssignmentID(Integer assignmentID) {
		this.assignmentID = assignmentID;
	}
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
