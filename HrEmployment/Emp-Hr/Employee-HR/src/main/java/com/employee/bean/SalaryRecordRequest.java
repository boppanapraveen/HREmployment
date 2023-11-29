package com.employee.bean;

public class SalaryRecordRequest {
	
	private Integer salary;
	private String startDate;
	private String month;
	private String endDate;
	private Integer employeeID;
	private Integer hrEmployeeID;
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public Integer getHrEmployeeID() {
		return hrEmployeeID;
	}
	public void setHrEmployeeID(Integer hrEmployeeID) {
		this.hrEmployeeID = hrEmployeeID;
	}
	
	


}
