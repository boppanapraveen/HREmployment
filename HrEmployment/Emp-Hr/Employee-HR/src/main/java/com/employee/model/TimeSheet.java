package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "timesheet")
public class TimeSheet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer timesheetID;
	private String date;
	private Long hoursWorked;
    @ManyToOne
    @JoinColumn(name = "empID", nullable = false)
    private Employee employee;
	public Integer getTimesheetID() {
		return timesheetID;
	}
	public void setTimesheetID(Integer timesheetID) {
		this.timesheetID = timesheetID;
	}
	public String getDate() {
		return date;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(Long hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

}
