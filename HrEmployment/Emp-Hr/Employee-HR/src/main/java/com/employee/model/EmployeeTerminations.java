package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmployeeTerminations")
public class EmployeeTerminations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer terminationID;
	private String terminationDate;
	private String reason;
    @ManyToOne
    @JoinColumn(name = "empID",nullable = false)
    private Employee employee;
	public Integer getTerminationID() {
		return terminationID;
	}
	public void setTerminationID(Integer terminationID) {
		this.terminationID = terminationID;
	}
	public String getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}
	public String getReaseon() {
		return reason;
	}
	public void setReaseon(String reason) {
		this.reason = reason;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    

}
