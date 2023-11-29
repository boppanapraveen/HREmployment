package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.SalaryRecordRequest;
import com.employee.service.SalaryRecordService;

@RestController
@RequestMapping("/api/salary")
public class SalaryRecordController {
	
	@Autowired
	SalaryRecordService salaryRecordService;
	
	@PostMapping("/")
	public ResponseEntity<?> addSalaryRecord(@RequestBody SalaryRecordRequest salaryRecordRequest) throws Exception {
		return new ResponseEntity<>(salaryRecordService.addSalaryRecord(salaryRecordRequest), HttpStatus.CREATED);
	}

	@GetMapping("/{employeeID}")
	public ResponseEntity<?> getSalaryRecordByEmployeeID(@PathVariable Integer employeeID) throws Exception {
		return new ResponseEntity<>(salaryRecordService.getSalaryRecordByEmployeeID(employeeID), HttpStatus.OK);
	}

}
