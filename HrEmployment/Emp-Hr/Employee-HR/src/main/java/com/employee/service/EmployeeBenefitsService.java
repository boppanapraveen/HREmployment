package com.employee.service;

import java.util.List;

import com.employee.bean.EmpBenefitsRequest;
import com.employee.model.EmployeeBenefits;

public interface EmployeeBenefitsService {
	public EmployeeBenefits addEmployeeBenefits(EmpBenefitsRequest request) throws Exception;
	public List<EmployeeBenefits> fetchAllBenefits() throws Exception;
}
