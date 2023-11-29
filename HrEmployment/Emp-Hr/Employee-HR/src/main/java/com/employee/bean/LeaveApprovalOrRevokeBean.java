package com.employee.bean;

public class LeaveApprovalOrRevokeBean {
	
	private Integer hrEmployeeID;
	private String status;
	private String comments;
	private Integer leaveRequestID;
	
	public Integer getHrEmployeeID() {
		return hrEmployeeID;
	}
	public void setHrEmployeeID(Integer hrEmployeeID) {
		this.hrEmployeeID = hrEmployeeID;
	}
	public Integer getLeaveRequestID() {
		return leaveRequestID;
	}
	public void setLeaveRequestID(Integer leaveRequestID) {
		this.leaveRequestID = leaveRequestID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	

}
