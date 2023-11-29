package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.AuthBean;
import com.employee.bean.EmployeeBean;
import com.employee.bean.ResponseBean;
import com.employee.bean.TerminateRequest;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/")
	public ResponseEntity<?> registerEmployee(@RequestBody EmployeeBean employeeBean) throws Exception{
		ResponseBean responseBean = employeeService.registerEmployee(employeeBean);
		return new ResponseEntity<>(responseBean,HttpStatus.CREATED);
	}
	
	@GetMapping("/{employeeID}")
	public ResponseEntity<?> fetchEmployeeByEmployeeID(@PathVariable Integer employeeID) throws Exception{
		return new ResponseEntity<>(employeeService.fetchEmployeeByEmployeeID(employeeID),HttpStatus.OK);
	}
	
	@GetMapping("/all/{employeeID}")
	public ResponseEntity<?> fetchAllEmployees(@PathVariable Integer employeeID) throws Exception{
		return new ResponseEntity<>(employeeService.fetchAllEmployees(employeeID),HttpStatus.OK);
	}
	
	@PutMapping("/terminate")
	public ResponseEntity<?> terminateEmployee(@RequestBody TerminateRequest req) throws Exception{
		return new ResponseEntity<>(employeeService.terminateEmployee(req),HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthBean authBean) throws Exception{
		return new ResponseEntity<>(employeeService.authenticateEmployee(authBean),HttpStatus.OK);
	}
	
	@PutMapping("/update/{employeeID}")
	public ResponseEntity<?> updateDetails(@RequestBody EmployeeBean employeeBean,@PathVariable Integer employeeID) throws Exception {
		return new ResponseEntity<>(employeeService.updateEmployee(employeeBean,employeeID),HttpStatus.OK);
	}

}
