package com.employee.service;

import java.util.List;

import com.employee.bean.ReviewRequest;
import com.employee.bean.SkillRequest;
import com.employee.model.EmployeeSkills;
import com.employee.model.PerformanceReview;

public interface PerformanceReviewService {
	public PerformanceReview reviewAnEmployee(ReviewRequest request)throws Exception;
	public List<PerformanceReview> fetchListOfReviews(Integer empID)throws Exception;
	public EmployeeSkills addEmployeeSkills(SkillRequest request) throws Exception;
	public List<EmployeeSkills> fetchEmployeeSkills(Integer empID) throws Exception;
}
