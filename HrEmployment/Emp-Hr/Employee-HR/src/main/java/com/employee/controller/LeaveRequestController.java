package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.LeaveApprovalOrRevokeBean;
import com.employee.bean.LeaveRequestBean;
import com.employee.service.LeaveRequestService;

@RestController
@RequestMapping("/api/leave")
public class LeaveRequestController {

	@Autowired
	LeaveRequestService leaveRequestService;

	@PostMapping("/")
	public ResponseEntity<?> applyLeave(@RequestBody LeaveRequestBean leaveReq) throws Exception {
		return new ResponseEntity<>(leaveRequestService.applyLeave(leaveReq), HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<?> approveOrRevokeLeaves(@RequestBody LeaveApprovalOrRevokeBean leaveApprovalOrRevokeBean)
			throws Exception {
		return new ResponseEntity<>(leaveRequestService.approveOrRevokeLeaves(leaveApprovalOrRevokeBean),
				HttpStatus.OK);
	}

	@GetMapping("/all/{employeeID}")
	public ResponseEntity<?> getAllLeaveRequest(@PathVariable Integer employeeID) throws Exception {
		return new ResponseEntity<>(leaveRequestService.getAllLeaveRequest(employeeID), HttpStatus.OK);
	}

	@GetMapping("/{employeeID}")
	public ResponseEntity<?> fetchLeaveRequestByEmpID(@PathVariable Integer employeeID) throws Exception {
		return new ResponseEntity<>(leaveRequestService.fetchLeaveRequestByEmpID(employeeID), HttpStatus.OK);
	}
	
	@PutMapping("/{leaveRequestID}")
	public ResponseEntity<?> updateLeaveRequest(@PathVariable Integer leaveRequestID,@RequestBody LeaveRequestBean leaveReq) throws Exception {
		return new ResponseEntity<>(leaveRequestService.updateLeaveRequest(leaveRequestID,leaveReq), HttpStatus.OK);
	}
}
