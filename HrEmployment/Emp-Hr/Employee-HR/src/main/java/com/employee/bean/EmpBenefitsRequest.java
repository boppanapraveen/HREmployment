package com.employee.bean;

public class EmpBenefitsRequest {
	private String benefitType;
	private String eligibleEmployees;
	private Integer empID;
	private String description;
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	public String getBenefitType() {
		return benefitType;
	}
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}
	public String getEligibleEmployees() {
		return eligibleEmployees;
	}
	public void setEligibleEmployees(String eligibleEmployees) {
		this.eligibleEmployees = eligibleEmployees;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
