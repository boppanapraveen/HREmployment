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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.EmployeeAppraisalsRequest;
import com.employee.service.EmployeeAppraisalsService;

@RestController
@RequestMapping("/api")
public class EmployeeAppraisalsController {
	
	@Autowired
	EmployeeAppraisalsService employeeAppraisalsService;
	
    @PostMapping("/appraisals/")
    public ResponseEntity<?> addAppraisals(@RequestBody EmployeeAppraisalsRequest request) throws Exception {
        return new ResponseEntity<>(employeeAppraisalsService.addAppraisals(request),HttpStatus.CREATED);
    }
    
    @GetMapping("/appraisals/{employeeID}")
    public ResponseEntity<?> fetchAllAppraisals(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(employeeAppraisalsService.fetchAllAppraisals(employeeID),HttpStatus.OK);
    }
    
    @PostMapping("promotions/")
    public ResponseEntity<?> punchOutAttendance(@RequestParam Integer empID,@RequestParam Integer hrEmpID) throws Exception {
        return new ResponseEntity<>(employeeAppraisalsService.promoteEmployee(empID,hrEmpID),HttpStatus.OK);
    }
    
    @GetMapping("/promotions/{employeeID}")
    public ResponseEntity<?> fetchTimeSheet(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(employeeAppraisalsService.fetchEmployeePromotions(employeeID),HttpStatus.OK);
    }

	

}
