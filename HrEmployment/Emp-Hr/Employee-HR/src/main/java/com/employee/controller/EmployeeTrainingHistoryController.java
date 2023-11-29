package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.EmpTrainingHistoryRequest;
import com.employee.service.EmployeeTrainingHistoryService;

@RestController
@RequestMapping("/api/training")
public class EmployeeTrainingHistoryController {
	
	@Autowired
	EmployeeTrainingHistoryService employeeTrainingHistoryService;
	
    @PostMapping("/")
    public ResponseEntity<?> assignTrainings(@RequestBody  EmpTrainingHistoryRequest empTrainingHistoryRequest) throws Exception {
        return new ResponseEntity<>(employeeTrainingHistoryService.assignTrainings(empTrainingHistoryRequest),HttpStatus.CREATED);
    }
    
    @GetMapping("/{employeeID}")
    public ResponseEntity<?> fetchAssignedTrainings(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(employeeTrainingHistoryService.fetchAssignedTrainings(employeeID),HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{trainingID}")
    public ResponseEntity<?> markCompletedTrainings(@PathVariable Integer trainingID,@RequestParam Integer empID) throws Exception {
        return new ResponseEntity<>(employeeTrainingHistoryService.markCompletedTrainings(trainingID,empID),HttpStatus.CREATED);
    }
    
    
}
