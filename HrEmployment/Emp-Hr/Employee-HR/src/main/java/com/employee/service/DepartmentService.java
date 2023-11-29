package com.employee.service;

import java.util.List;

import com.employee.bean.DepartmentRequest;
import com.employee.model.Department;

public interface DepartmentService {
	public Department addDepartment(DepartmentRequest deptRequest) throws Exception;
	
	public Department fetchDepartment(Integer deptID) throws Exception;
	
	public String deleteDepartment(Integer deptID,Integer employeeID) throws Exception;
	
	public List<Department> getAllDepartments() throws Exception;
	
	public Department updateDepartment(Integer deptID, DepartmentRequest deptRequest) throws Exception;
}
