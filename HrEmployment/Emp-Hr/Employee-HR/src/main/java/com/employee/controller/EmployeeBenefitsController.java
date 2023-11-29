package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.EmpBenefitsRequest;
import com.employee.service.EmployeeBenefitsService;

@RestController
@RequestMapping("/api/benefits")
public class EmployeeBenefitsController {
	
	@Autowired
	EmployeeBenefitsService employeeBenefitsService;
	
    @PostMapping("/")
    public ResponseEntity<?> addEmployeeBenefits(@RequestBody EmpBenefitsRequest request) throws Exception {
        return new ResponseEntity<>(employeeBenefitsService.addEmployeeBenefits(request),HttpStatus.CREATED);
    }
    
    @GetMapping("/")
    public ResponseEntity<?> fetchAllBenefits() throws Exception {
        return new ResponseEntity<>(employeeBenefitsService.fetchAllBenefits(),HttpStatus.OK);
    }


}
