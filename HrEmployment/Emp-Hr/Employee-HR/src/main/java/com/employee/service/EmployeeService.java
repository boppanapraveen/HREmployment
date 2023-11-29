package com.employee.service;

import java.util.List;

import com.employee.bean.AuthBean;
import com.employee.bean.AuthVerified;
import com.employee.bean.EmployeeBean;
import com.employee.bean.MessageResponse;
import com.employee.bean.ResponseBean;
import com.employee.bean.TerminateRequest;
import com.employee.model.Employee;

public interface EmployeeService {
	
	public ResponseBean registerEmployee(EmployeeBean employeeBean) throws Exception;
	
	public Employee fetchEmployeeByEmployeeID(Integer employeeID) throws Exception;

	public Employee getEmployeeModel(Integer employeeID) throws Exception;
	
	public List<Employee> fetchAllEmployees(Integer employeeID) throws Exception;
	
	public MessageResponse terminateEmployee(TerminateRequest req) throws Exception;
	
	public AuthVerified authenticateEmployee(AuthBean authBean)throws Exception;  
	
	public Employee updateEmployee(EmployeeBean employeeBean,Integer empID) throws Exception; 
	
}
