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

import com.employee.bean.ReviewRequest;
import com.employee.bean.SkillRequest;
import com.employee.service.PerformanceReviewService;

@RestController
@RequestMapping("/api/performance")
public class PerformanceReviewController {
	
	@Autowired
	PerformanceReviewService performanceReviewService;
	
    @PostMapping("/")
    public ResponseEntity<?> reviewAnEmployee(@RequestBody ReviewRequest request) throws Exception {
        return new ResponseEntity<>(performanceReviewService.reviewAnEmployee(request),HttpStatus.CREATED);
    }
    
    @GetMapping("/{employeeID}")
    public ResponseEntity<?> fetchListOfReviews(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(performanceReviewService.fetchListOfReviews(employeeID),HttpStatus.OK);
    }
    
    @PostMapping("/skills")
    public ResponseEntity<?> addEmployeeSkills(@RequestBody SkillRequest request) throws Exception {
        return new ResponseEntity<>(performanceReviewService.addEmployeeSkills(request),HttpStatus.CREATED);
    }
    
    @GetMapping("/skills/{employeeID}")
    public ResponseEntity<?> fetchEmployeeSkills(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(performanceReviewService.fetchEmployeeSkills(employeeID),HttpStatus.OK);
    }
}
