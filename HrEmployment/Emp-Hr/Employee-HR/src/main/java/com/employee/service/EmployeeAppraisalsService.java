package com.employee.service;

import java.util.List;

import com.employee.bean.EmployeeAppraisalsRequest;
import com.employee.model.EmployeeAppraisals;
import com.employee.model.EmployeePromotions;

public interface EmployeeAppraisalsService {
	
	public EmployeeAppraisals addAppraisals(EmployeeAppraisalsRequest request) throws Exception;
	public List<EmployeeAppraisals> fetchAllAppraisals(Integer empID) throws Exception;
	public EmployeePromotions promoteEmployee(Integer empID,Integer hrEmpID) throws Exception;
	public List<EmployeePromotions> fetchEmployeePromotions(Integer empID) throws Exception;

}
