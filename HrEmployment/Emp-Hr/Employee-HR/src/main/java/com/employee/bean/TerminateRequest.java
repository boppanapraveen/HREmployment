package com.employee.bean;

public class TerminateRequest {
	
	private Integer empId; 
	private Integer hrEmpID;
	private String reason;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getHrEmpID() {
		return hrEmpID;
	}
	public void setHrEmpID(Integer hrEmpID) {
		this.hrEmpID = hrEmpID;
	}
	public String getReaseon() {
		return reason;
	}
	public void setReaseon(String reason) {
		this.reason = reason;
	}


}
