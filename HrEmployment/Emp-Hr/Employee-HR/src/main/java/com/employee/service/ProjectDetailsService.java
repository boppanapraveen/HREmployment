package com.employee.service;

import java.util.List;

import com.employee.bean.ProjectAssignmentsRequest;
import com.employee.bean.ProjectDetailsRequest;
import com.employee.model.ProjectAssignments;
import com.employee.model.ProjectDetails;

public interface ProjectDetailsService {
	
	public ProjectDetails addProjectDetails(ProjectDetailsRequest request) throws Exception;
	public List<ProjectDetails> fetchProjectDetailsBasedOnDepartment(Integer deptID)throws Exception;
	public ProjectAssignments addAssignmentsForTheProject(ProjectAssignmentsRequest request)throws Exception;
	public List<ProjectAssignments> fetchProjectAssignmentsBasedOnProject(Integer projectID,Integer empID)throws Exception;  

}
