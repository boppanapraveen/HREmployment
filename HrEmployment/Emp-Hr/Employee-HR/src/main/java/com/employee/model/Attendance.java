package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attendenceID;
	private String date;
	private String clockInTime;
	private String clockOutTime;
	@ManyToOne
	@JoinColumn(name = "empID", nullable = false)
	private Employee employee;

	public Integer getAttendenceID() {
		return attendenceID;
	}

	public void setAttendenceID(Integer attendenceID) {
		this.attendenceID = attendenceID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getClockInTime() {
		return clockInTime;
	}

	public void setClockInTime(String clockInTime) {
		this.clockInTime = clockInTime;
	}

	public String getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(String clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
