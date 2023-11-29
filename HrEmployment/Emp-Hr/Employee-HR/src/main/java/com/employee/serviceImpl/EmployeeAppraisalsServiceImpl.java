package com.employee.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.EmployeeAppraisalsRequest;
import com.employee.bean.PromotionReq;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.EmployeeAppraisals;
import com.employee.model.EmployeePromotions;
import com.employee.repository.EmployeeAppraisalsRepo;
import com.employee.repository.EmployeePromotionsRepo;
import com.employee.repository.EmployeeRepo;
import com.employee.service.EmployeeAppraisalsService;

@Service
public class EmployeeAppraisalsServiceImpl implements EmployeeAppraisalsService{
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	EmployeeAppraisalsRepo employeeAppraisalsRepo; 
	
	@Autowired
	EmployeePromotionsRepo employeePromotionsRepo;

	@Override
	public EmployeeAppraisals addAppraisals(EmployeeAppraisalsRequest request) throws Exception {
		try {
			Optional<Employee> hrOptional = employeeRepo.findById(request.getHrEmpID());
			if(hrOptional.isEmpty() || !hrOptional.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "You Are Not Authorized to do"));
			}
			Optional<Employee> employee = employeeRepo.findById(request.getEmpID());
			if(employee.isEmpty() || employee.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			EmployeeAppraisals employeeAppraisals = new EmployeeAppraisals();
			employeeAppraisals.setAppraisalDate(request.getAppraisalDate());
			employeeAppraisals.setComments(request.getComments());
			employeeAppraisals.setRating(request.getRating());
			employeeAppraisals.setEmployee(employee.get());
			EmployeeAppraisals savedAppraisals = employeeAppraisalsRepo.save(employeeAppraisals);
			return savedAppraisals;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while saving EmployeeAppraisals"));
		}
	}

	@Override
	public List<EmployeeAppraisals> fetchAllAppraisals(Integer empID) throws Exception {
		try {
			Optional<Employee> hrOptional = employeeRepo.findById(empID);
			if(hrOptional.isEmpty() || !hrOptional.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "You Are Not Authorized to do"));
			}
			return employeeAppraisalsRepo.findAll();
		}catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching EmployeeAppraisals"));
		}
	}

	@Override
	public EmployeePromotions promoteEmployee(Integer empID,Integer hrEmpID) throws Exception {
		try {
			Optional<Employee> hrOptional = employeeRepo.findById(hrEmpID);
			if(hrOptional.isEmpty() || !hrOptional.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "You Are Not Authorized to do"));
			}
			Optional<Employee> employee = employeeRepo.findById(empID);
			if(employee.isEmpty() || employee.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			EmployeePromotions employeePromotions = new EmployeePromotions();
			employeePromotions.setPromotionDate(getCurrentDateAsString());
			employeePromotions.setEmployee(employee.get());
			EmployeePromotions savedEmployeePromotions = employeePromotionsRepo.save(employeePromotions);
			return savedEmployeePromotions;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while promoting an Employee"));
		}
	}
	
	private String getCurrentDateAsString() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}

	@Override
	public List<EmployeePromotions> fetchEmployeePromotions(Integer empID) throws Exception {
		try {
			Optional<Employee> hrOptional = employeeRepo.findById(empID);
			if(hrOptional.isEmpty() || hrOptional.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			List<EmployeePromotions> employeePromotions = employeePromotionsRepo.findByEmployeeEmpIDAndEmployeeActive(empID, 1);
			if(employeePromotions.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "No Records Found"));
			}
			return employeePromotions;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching EmployeePromotions"));
		}
	}
	
	

}
