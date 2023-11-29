package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.EmployeeEmergencyContacts;

@Repository
public interface EmployeeEmergencyContactsRepo extends JpaRepository<EmployeeEmergencyContacts, Integer>{
	public List<EmployeeEmergencyContacts> findByEmployeeEmpID(Integer empID);
}
