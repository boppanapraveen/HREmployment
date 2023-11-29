package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Department;
import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer>{
	
	public Department findByDepartmentName(String departmentName);

}
