package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmployeeBenefits")
public class EmployeeBenefits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer benefitID;
	private String benefitType;
	private String eligibleEmployees;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getBenefitID() {
		return benefitID;
	}
	public void setBenefitID(Integer benefitID) {
		this.benefitID = benefitID;
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
	
    
}
