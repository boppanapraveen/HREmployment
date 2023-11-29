package com.employee.bean;

public class DeductionBean {
	
	private String deductionType;
	private Integer rate;
	private String effectiveDate;
	private Integer employeeID;
	private Integer hrEmployeeID;
	private String month;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getHrEmployeeID() {
		return hrEmployeeID;
	}
	public void setHrEmployeeID(Integer hrEmployeeID) {
		this.hrEmployeeID = hrEmployeeID;
	}
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getDeductionType() {
		return deductionType;
	}
	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	

}
