package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.EmpBenefitsRequest;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.EmployeeBenefits;
import com.employee.repository.EmployeeBenefitsRepo;
import com.employee.repository.EmployeeRepo;
import com.employee.service.EmployeeBenefitsService;

@Service
public class EmployeeBenefitsServiceImpl implements EmployeeBenefitsService{

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	EmployeeBenefitsRepo employeeBenefitsRepo;
	
	@Override
	public EmployeeBenefits addEmployeeBenefits(EmpBenefitsRequest request) throws Exception {
		try {
			Optional<Employee> employee = employeeRepo.findById(request.getEmpID());
			if(employee.isEmpty() || !employee.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "You are Not Authorized"));
			}
			EmployeeBenefits employeeBenefits = new EmployeeBenefits();
			employeeBenefits.setBenefitType(request.getBenefitType());
			employeeBenefits.setEligibleEmployees(request.getEligibleEmployees());
			employeeBenefits.setDescription(request.getDescription());
			EmployeeBenefits savedBenefits = employeeBenefitsRepo.save(employeeBenefits);
			return savedBenefits;
		}  catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while saving EmployeeBenefits"));
		}
	}

	@Override
	public List<EmployeeBenefits> fetchAllBenefits() throws Exception {
		try {
			List<EmployeeBenefits> employeeBenefits = employeeBenefitsRepo.findAll();
			if(employeeBenefits.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "No Records Found In EmployeeBenefits Table"));
			}
			return employeeBenefits;
		}  catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching EmployeeBenefits"));
		}
	}

}
