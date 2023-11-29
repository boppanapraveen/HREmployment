package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employeeEmergencyCotacts")
public class EmployeeEmergencyContacts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empEmrID;
	private String name;
	private String relation;
	private String contactNo;
    @ManyToOne
    @JoinColumn(name = "empID", nullable = false)
    private Employee employee;
	public Integer getEmpEmrID() {
		return empEmrID;
	}
	public void setEmpEmrID(Integer empEmrID) {
		this.empEmrID = empEmrID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    

}
