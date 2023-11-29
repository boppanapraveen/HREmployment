package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.ReviewRequest;
import com.employee.bean.SkillRequest;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.EmployeeSkills;
import com.employee.model.HealthRecord;
import com.employee.model.PerformanceReview;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.EmployeeSkillsRepo;
import com.employee.repository.PerformanceReviewRepo;
import com.employee.service.PerformanceReviewService;
@Service
public class EmployeePerformanceReviewServiceImpl implements PerformanceReviewService{
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	PerformanceReviewRepo performanceReviewRepo;
	
	@Autowired
	EmployeeSkillsRepo employeeSkillsRepo;

	@Override
	public PerformanceReview reviewAnEmployee(ReviewRequest request) throws Exception {
		try {
			Optional<Employee> optional = employeeRepo.findById(request.getHrEmpID());
			if(optional.isEmpty() || !optional.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "You are Not Authorized"));
			}
			Optional<Employee> employee = employeeRepo.findById(request.getEmpID());
			if(employee.isEmpty() || employee.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			PerformanceReview performanceReview = new PerformanceReview();
			performanceReview.setEmployee(employee.get());
			performanceReview.setFeedback(request.getFeedback());
			performanceReview.setPerformanceRating(request.getPerformanceRating());
			performanceReview.setReviewDate(request.getReviewDate());
			PerformanceReview savedPerformanceReview = performanceReviewRepo.save(performanceReview);
			return savedPerformanceReview;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while saving PerformanceReview"));
		}
	}

	@Override
	public List<PerformanceReview> fetchListOfReviews(Integer empID) throws Exception {
		try {
			List<PerformanceReview> performanceReviews = performanceReviewRepo.findByEmployeeEmpIDAndEmployeeActive(empID, 1);
			if(performanceReviews.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "records Not found for that Employee"));
			}
			return performanceReviews;
		}  catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching PerformanceReview"));
		}
	}

	@Override
	public EmployeeSkills addEmployeeSkills(SkillRequest request) throws Exception {
		try {
			Optional<Employee> employee = employeeRepo.findById(request.getEmpID());
			if(employee.isEmpty() || employee.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			EmployeeSkills employeeSkills = new EmployeeSkills();
			employeeSkills.setEmployee(employee.get());
			employeeSkills.setSkillLevel(request.getSkillLevel());
			employeeSkills.setSkillName(request.getSkillName());
			EmployeeSkills savedSkills = employeeSkillsRepo.save(employeeSkills);
			return savedSkills;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while saving EmployeeSkills"));
		}
	}

	@Override
	public List<EmployeeSkills> fetchEmployeeSkills(Integer empID) throws Exception {
		try {
			List<EmployeeSkills> employeeSkills = employeeSkillsRepo.findByEmployeeEmpIDAndEmployeeActive(empID, 1);
			if(employeeSkills.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "records Not found for that Employee"));
			}
			return employeeSkills;
		}  catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching EmployeeSkills"));
		}
	}

}
