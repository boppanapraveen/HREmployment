package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.LeaveApprovalOrRevokeBean;
import com.employee.bean.LeaveRequestBean;
import com.employee.bean.ResponseBean;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.LeaveRequest;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.LeaveRequestRepo;
import com.employee.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

	@Autowired
	LeaveRequestRepo leaveRequestRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public ResponseBean applyLeave(LeaveRequestBean leaveReq) throws Exception {
		try {

			// TODO Auto-generated method stub
			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest.setLeaveType(leaveReq.getLeaveType());
			leaveRequest.setStartDate(leaveReq.getStartDate());
			leaveRequest.setEndDate(leaveReq.getEndDate());
			Optional<Employee> employee = employeeRepo.findById(leaveReq.getEmployeeID());
			if (employee.isEmpty() || employee.get().getActive() == 0) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee, please check employee ID"));

			}
			leaveRequest.setEmployee(employee.get());
			leaveRequest.setStatus("pending");
			LeaveRequest savedLeaveRequest = leaveRequestRepo.save(leaveRequest);
			if (savedLeaveRequest == null) {
				throw new EmployeeHRException(HttpStatus.BAD_REQUEST,
						new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "Could not apply for leave"));

			}
			ResponseBean responseBean = new ResponseBean();
			responseBean.setEmployeeID(leaveReq.getEmployeeID());
			responseBean.setStatus("Applied For Leave");
			return responseBean;

		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while applying for a leave"));
		}
	}

	@Override
	public LeaveRequest approveOrRevokeLeaves(LeaveApprovalOrRevokeBean leaveApprovalOrRevokeBean) throws Exception {
		// TODO Auto-generated method stub
		ResponseBean responseBean = null;
		try {
			Employee employee = employeeRepo.findById(leaveApprovalOrRevokeBean.getHrEmployeeID()).get();
			if (employee == null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee, please check employee ID"));
			}
			if (employee.getRole().equals("HR")) {
				LeaveRequest leaveRequest = leaveRequestRepo
						.findById(leaveApprovalOrRevokeBean.getLeaveRequestID()).get();
				leaveRequest.setStatus(leaveApprovalOrRevokeBean.getStatus());
				leaveRequest.setComments(leaveApprovalOrRevokeBean.getComments());
			    LeaveRequest updatedLeaveRequest = leaveRequestRepo.save(leaveRequest);

				return updatedLeaveRequest;
			} else {
				throw new EmployeeHRException(HttpStatus.BAD_REQUEST,
						new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "You Dont Have Access to approve"));
			}

		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while approving for a leave"));
		}
	}

	@Override
	public List<LeaveRequest> getAllLeaveRequest(Integer empID) throws Exception {
		// TODO Auto-generated method stub
		try {
			Employee employee = employeeRepo.findById(empID).get();
			if (employee == null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee, please check employee ID"));
			}
			if (employee.getRole().equals("HR")) {
				List<LeaveRequest> leaveRequests = leaveRequestRepo.findAll();
				return leaveRequests;
			} else {
				throw new EmployeeHRException(HttpStatus.BAD_REQUEST,
						new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "You Dont Have Access to approve"));
			}
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching all leaves"));
		}
	}

	@Override
	public List<LeaveRequest> fetchLeaveRequestByEmpID(Integer empID) throws Exception {
		// TODO Auto-generated method stub
		try {
			List<LeaveRequest> leaveRequest = leaveRequestRepo.findByEmployeeEmpIDAndEmployeeActive(empID,1);
			if (leaveRequest == null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find leave requests for an employee, please check employee ID"));
			}

			return leaveRequest;

		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching leaves"));
		}
	}

	@Override
	public LeaveRequest updateLeaveRequest(Integer leaveRequestID,LeaveRequestBean leaveReq) throws Exception {
		try {
			Optional<LeaveRequest> leaveOptional = leaveRequestRepo.findById(leaveRequestID);
			if(leaveOptional.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find leave requests please check requestID : " + leaveRequestID));
			}
			LeaveRequest leaveRequest = leaveOptional.get();
			leaveRequest.setLeaveType(leaveReq.getLeaveType());
			leaveRequest.setStartDate(leaveReq.getStartDate());
			leaveRequest.setEndDate(leaveReq.getEndDate());
			LeaveRequest savedLeaveRequest = leaveRequestRepo.save(leaveRequest);
			return savedLeaveRequest;
		}  catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while updating leaves"));
		}
	}
	
	

}
