package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PayrollDeductions")
public class PayrollDeductions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deductionID;
	private String deductionType;
	private Integer rate;
	private String effectiveDate;
	private String month;
    @ManyToOne
    @JoinColumn(name = "salID",nullable = false)
    private SalaryRecord salaryRecord;
	
	public SalaryRecord getSalaryRecord() {
		return salaryRecord;
	}
	public void setSalaryRecord(SalaryRecord salaryRecord) {
		this.salaryRecord = salaryRecord;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getDeductionID() {
		return deductionID;
	}
	public void setDeductionID(Integer deductionID) {
		this.deductionID = deductionID;
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
