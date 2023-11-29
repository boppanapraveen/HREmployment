package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.ProjectDetails;

@Repository
public interface ProjectDetailsRepo extends JpaRepository<ProjectDetails, Integer>{
	
	public List<ProjectDetails> findByDepartmentDeptID(Integer deptID);
}
