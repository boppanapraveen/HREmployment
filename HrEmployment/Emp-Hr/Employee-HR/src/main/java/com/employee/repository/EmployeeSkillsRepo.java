package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.EmployeeSkills;

@Repository
public interface EmployeeSkillsRepo extends JpaRepository<EmployeeSkills, Integer>{
	
	public List<EmployeeSkills> findByEmployeeEmpIDAndEmployeeActive(Integer empID,Integer active);

}
