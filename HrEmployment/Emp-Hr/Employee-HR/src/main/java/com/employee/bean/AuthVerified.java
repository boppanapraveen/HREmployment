package com.employee.bean;

public class AuthVerified {
	
	private String status;
	private String role;
	private Integer empID;
	private String firstName;
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public AuthVerified(String status, String role, Integer empID, String firstName, String lastName) {
		super();
		this.status = status;
		this.role = role;
		this.empID = empID;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public AuthVerified() {
		super();
	}
	

}
