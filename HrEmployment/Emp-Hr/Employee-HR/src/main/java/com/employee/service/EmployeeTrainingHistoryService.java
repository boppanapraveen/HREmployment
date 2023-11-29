package com.employee.service;

import java.util.List;

import com.employee.bean.EmpTrainingHistoryRequest;
import com.employee.bean.MessageResponse;
import com.employee.model.EmployeeTrainingHistory;

public interface EmployeeTrainingHistoryService {
	public EmployeeTrainingHistory assignTrainings(EmpTrainingHistoryRequest empTrainingHistoryRequest) throws Exception;
	
	public List<EmployeeTrainingHistory> fetchAssignedTrainings(Integer empID)throws Exception;
	
	public MessageResponse markCompletedTrainings(Integer trainingID,Integer hrEmpID)throws Exception;
	
}
