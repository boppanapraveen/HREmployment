package com.employee.service;

import java.util.List;

import com.employee.bean.LeaveApprovalOrRevokeBean;
import com.employee.bean.LeaveRequestBean;
import com.employee.bean.ResponseBean;
import com.employee.model.LeaveRequest;

public interface LeaveRequestService {
	
	public ResponseBean applyLeave(LeaveRequestBean leaveReq) throws Exception;
	
	public LeaveRequest approveOrRevokeLeaves(LeaveApprovalOrRevokeBean leaveApprovalOrRevokeBean) throws Exception;
	
	public List<LeaveRequest> getAllLeaveRequest(Integer empID) throws Exception;
	
	public List<LeaveRequest> fetchLeaveRequestByEmpID(Integer empID) throws Exception;
	
	public LeaveRequest updateLeaveRequest(Integer leaveRequestID,LeaveRequestBean leaveReq) throws Exception;

}
