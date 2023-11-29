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

import com.employee.bean.HealthRecordRequest;
import com.employee.service.EmployeeHealthRecordsService;

@RestController
@RequestMapping("/api/health")
public class EmployeeHealthRecordsController {
	
	@Autowired
	EmployeeHealthRecordsService employeeHealthRecordsService;
	
    @PostMapping("/")
    public ResponseEntity<?> addHealthRecord(@RequestBody HealthRecordRequest recordRequest) throws Exception {
        return new ResponseEntity<>(employeeHealthRecordsService.addHealthRecord(recordRequest),HttpStatus.CREATED);
    }
    
    @GetMapping("/{employeeID}")
    public ResponseEntity<?> fetcHealthRecord(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(employeeHealthRecordsService.fetcHealthRecord(employeeID),HttpStatus.OK);
    }

}
