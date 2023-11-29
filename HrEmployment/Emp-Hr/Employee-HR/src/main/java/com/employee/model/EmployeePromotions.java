package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "employee_promotions")
public class EmployeePromotions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer promotionID;
	private String promotionDate;
    @ManyToOne
    @JoinColumn(name = "empID",nullable = false)
    private Employee employee;
	public Integer getPromotionID() {
		return promotionID;
	}
	public void setPromotionID(Integer promotionID) {
		this.promotionID = promotionID;
	}
	public String getPromotionDate() {
		return promotionDate;
	}
	public void setPromotionDate(String promotionDate) {
		this.promotionDate = promotionDate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    
	

}
