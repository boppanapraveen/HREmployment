package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.ProjectAssignments;

@Repository
public interface ProjectAssignmentsRepo extends JpaRepository<ProjectAssignments, Integer>{
	public List<ProjectAssignments> findByProjectDetailsProjectIDAndEmployeeEmpID(Integer projectID,Integer empID);
}
