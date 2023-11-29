package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.model.PayrollDeductions;

@Repository
public interface PayrollDeductionRepo extends JpaRepository<PayrollDeductions, Integer>{
	
	@Query("SELECT pay FROM PayrollDeductions pay WHERE pay.salaryRecord.employee.empID = :employeeID AND pay.salaryRecord.employee.active = 1")
	public List<PayrollDeductions> findByEmployeeID(Integer employeeID);
	
}
