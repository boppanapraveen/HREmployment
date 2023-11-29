package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.HealthRecord;

@Repository
public interface HealthRecordRepo extends JpaRepository<HealthRecord, Integer>{
	public HealthRecord findByEmployeeEmpIDAndEmployeeActive(Integer empID,Integer active);
}
