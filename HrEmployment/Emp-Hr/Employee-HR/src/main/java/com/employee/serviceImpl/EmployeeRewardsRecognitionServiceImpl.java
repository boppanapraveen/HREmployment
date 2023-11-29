package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.RewardsReq;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.EmployeeRewardsRecognition;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.EmployeeRewardsRepo;
import com.employee.service.EmployeeRewardsRecognitionService;

@Service
public class EmployeeRewardsRecognitionServiceImpl implements EmployeeRewardsRecognitionService{

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	EmployeeRewardsRepo employeeRewardsRepo;
	
	@Override
	public EmployeeRewardsRecognition addRecognitionToEmployee(RewardsReq rewardsReq) throws Exception {
		try {
			Optional<Employee> hrOptional = employeeRepo.findById(rewardsReq.getHrEmpID());
			if(hrOptional.isEmpty() || !hrOptional.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "You Are Not Authorized to do"));
			}
			Optional<Employee> employee = employeeRepo.findById(rewardsReq.getEmpID());
			if(employee.isEmpty() || employee.get().getActive() == 0) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			EmployeeRewardsRecognition employeeRewardsRecognition = new EmployeeRewardsRecognition();
			employeeRewardsRecognition.setDescription(rewardsReq.getDescription());
			employeeRewardsRecognition.setRecognitionType(rewardsReq.getRecognitionType());
			employeeRewardsRecognition.setEmployee(employee.get());
			EmployeeRewardsRecognition savedEmployeeRewardsRecognition = employeeRewardsRepo.save(employeeRewardsRecognition);
			return savedEmployeeRewardsRecognition;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while saving EmployeeRewardRecognition"));
		}
	}

	@Override
	public List<EmployeeRewardsRecognition> fetchEmployeeRewardsRecognitions(Integer empID) throws Exception {
		try {
			Optional<Employee> hrOptional = employeeRepo.findById(empID);
			if(hrOptional.isEmpty() || !hrOptional.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "You Are Not Authorized to do"));
			}
			return employeeRewardsRepo.findAll();
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching EmployeeRewards"));
		}
	}

	@Override
	public List<EmployeeRewardsRecognition> fetchSpecificEmployeeRewardsRecognitions(Integer empID) throws Exception {
		try {
			Optional<Employee> hrOptional = employeeRepo.findById(empID);
			if(hrOptional.isEmpty() || hrOptional.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			return employeeRewardsRepo.findByEmployeeEmpID(empID);
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching EmployeeRewards"));
		}

	}
	
}
