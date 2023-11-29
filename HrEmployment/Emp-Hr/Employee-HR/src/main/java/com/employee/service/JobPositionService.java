package com.employee.service;

import java.util.List;

import com.employee.bean.JobPositionRequest;
import com.employee.bean.ResponseBean;
import com.employee.model.JobPosition;

public interface JobPositionService {
	
	public ResponseBean addJobPositions(JobPositionRequest jobRequest) throws Exception;
	
	public List<JobPosition> getJobPositions() throws Exception;
	
	public ResponseBean deleteJobPosition(Integer employeeID,Integer jobID) throws Exception;
	
	public JobPosition updateJobPosition(Integer jobPositionID,JobPositionRequest jobRequest) throws Exception;
}
