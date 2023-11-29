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

import com.employee.bean.RewardsReq;
import com.employee.service.EmployeeRewardsRecognitionService;

@RestController
@RequestMapping("/api/rewards")
public class EmployeeRewardsController {
	
	@Autowired
	EmployeeRewardsRecognitionService employeeRewardsRecognitionService;
	
    @PostMapping("/")
    public ResponseEntity<?> addRecognitionToEmployee(@RequestBody RewardsReq rewardsReq) throws Exception {
        return new ResponseEntity<>(employeeRewardsRecognitionService.addRecognitionToEmployee(rewardsReq),HttpStatus.CREATED);
    }
    
    @GetMapping("/all/{employeeID}")
    public ResponseEntity<?> fetchEmployeeRewardsRecognitions(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(employeeRewardsRecognitionService.fetchEmployeeRewardsRecognitions(employeeID),HttpStatus.OK);
    }
    
    @GetMapping("/{employeeID}")
    public ResponseEntity<?> fetchSpecificEmployeeRewardsRecognitions(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(employeeRewardsRecognitionService.fetchSpecificEmployeeRewardsRecognitions(employeeID),HttpStatus.OK);
    }


}
