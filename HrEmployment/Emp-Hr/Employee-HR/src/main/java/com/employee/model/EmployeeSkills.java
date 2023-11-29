package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmployeeSkills")
public class EmployeeSkills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer skillID;
	private String skillName;
	private String skillLevel;
    @ManyToOne
    @JoinColumn(name = "empID", nullable = false)
    private Employee employee;
	public Integer getSkillID() {
		return skillID;
	}
	public void setSkillID(Integer skillID) {
		this.skillID = skillID;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    
    

}
