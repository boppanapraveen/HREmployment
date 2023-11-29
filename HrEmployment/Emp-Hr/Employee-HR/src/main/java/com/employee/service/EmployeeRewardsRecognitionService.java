package com.employee.service;

import java.util.List;

import com.employee.bean.RewardsReq;
import com.employee.model.EmployeeRewardsRecognition;

public interface EmployeeRewardsRecognitionService {
	
	public EmployeeRewardsRecognition addRecognitionToEmployee(RewardsReq rewardsReq) throws Exception;
	public List<EmployeeRewardsRecognition> fetchEmployeeRewardsRecognitions(Integer empID) throws Exception;
	public List<EmployeeRewardsRecognition> fetchSpecificEmployeeRewardsRecognitions(Integer empID) throws Exception;
}
