package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.EmployeeTrainingHistory;

@Repository
public interface EmployeeTrainingHistoryRepo extends JpaRepository<EmployeeTrainingHistory, Integer>{
	
	public List<EmployeeTrainingHistory> findByEmployeeEmpIDAndEmployeeActive(Integer empID,Integer active);
}
