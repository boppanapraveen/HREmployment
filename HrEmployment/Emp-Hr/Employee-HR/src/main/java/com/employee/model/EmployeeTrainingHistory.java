package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmployeeTrainingHistory")
public class EmployeeTrainingHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeTraingHistoryID;
	private String trainingName;
	private String trainingDate;
    @ManyToOne
    @JoinColumn(name = "empID", nullable = false)
    private Employee employee;

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Integer getEmployeeTraingHistoryID() {
		return employeeTraingHistoryID;
	}
	public void setEmployeeTraingHistoryID(Integer employeeTraingHistoryID) {
		this.employeeTraingHistoryID = employeeTraingHistoryID;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public String getTrainingDate() {
		return trainingDate;
	}
	public void setTrainingDate(String trainingDate) {
		this.trainingDate = trainingDate;
	}

}
