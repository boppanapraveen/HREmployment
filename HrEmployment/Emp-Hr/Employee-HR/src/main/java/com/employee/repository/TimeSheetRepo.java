package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.TimeSheet;

@Repository
public interface TimeSheetRepo extends JpaRepository<TimeSheet, Integer>{
	
	public List<TimeSheet> findByEmployeeEmpIDAndEmployeeActive(Integer empID,Integer active);
}
