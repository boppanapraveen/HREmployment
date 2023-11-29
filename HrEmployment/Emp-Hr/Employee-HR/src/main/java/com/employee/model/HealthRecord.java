package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "HealthRecord")
public class HealthRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer healthRecordID;
	private String medicalCondition;
	private String medications;
	private String allergies;
	private String field;
    @ManyToOne
    @JoinColumn(name = "empID", nullable = false)
    private Employee employee;
	public Integer getHealthRecord() {
		return healthRecordID;
	}
	public void setHealthRecord(Integer healthRecord) {
		this.healthRecordID = healthRecord;
	}
	public String getMedicalCondition() {
		return medicalCondition;
	}
	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}
	public String getMedications() {
		return medications;
	}
	public void setMedications(String medications) {
		this.medications = medications;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

    
}
