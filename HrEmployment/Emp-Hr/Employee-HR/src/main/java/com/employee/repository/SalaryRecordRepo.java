package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.model.SalaryRecord;

@Repository
public interface SalaryRecordRepo extends JpaRepository<SalaryRecord, Integer>{
	
	@Query("SELECT sal FROM SalaryRecord sal WHERE sal.employee.empID = :employeeID AND sal.employee.active = 1")
	public List<SalaryRecord> fetchSalaryRecords(Integer employeeID);
	
	public SalaryRecord findByMonthAndEmployeeEmpIDAndEmployeeActive(String month,Integer empID,Integer active);

}
