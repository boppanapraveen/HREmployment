package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.DeductionBean;
import com.employee.service.PayrollDeductionService;

@RestController
@RequestMapping("/api/payroll")
public class PayrollDeductionController {
	
	@Autowired
	PayrollDeductionService payrollDeductionService;
	
	@PostMapping("/")
	public ResponseEntity<?> addDeductions(@RequestBody DeductionBean deductionBean) throws Exception {
		return new ResponseEntity<>(payrollDeductionService.addDeductions(deductionBean), HttpStatus.CREATED);
	}

	@GetMapping("/{employeeID}")
	public ResponseEntity<?> getAllLeaveRequest(@PathVariable Integer employeeID) throws Exception {
		return new ResponseEntity<>(payrollDeductionService.fetchPayrollDeductions(employeeID), HttpStatus.OK);
	}

	@GetMapping("/all/{employeeID}")
	public ResponseEntity<?> fetchPayrollDeductionsList(@PathVariable Integer employeeID) throws Exception {
		return new ResponseEntity<>(payrollDeductionService.fetchPayrollDeductionsList(employeeID), HttpStatus.OK);
	}

	@PutMapping("/{deductionID}")
	public ResponseEntity<?> updatePayrollDeductions(@PathVariable Integer deductionID,@RequestBody DeductionBean deductionBean) throws Exception {
		return new ResponseEntity<>(payrollDeductionService.updatePayrollDeductions(deductionID,deductionBean), HttpStatus.OK);
	}

}
