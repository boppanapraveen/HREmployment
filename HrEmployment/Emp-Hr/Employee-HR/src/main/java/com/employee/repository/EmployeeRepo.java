package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	
	public Employee findByEmailAndActive(String email,Integer active);
}
