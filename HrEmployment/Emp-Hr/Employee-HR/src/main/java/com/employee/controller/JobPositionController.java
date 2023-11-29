package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.JobPositionRequest;
import com.employee.service.JobPositionService;

@RestController
@RequestMapping("/api/jobposition")
public class JobPositionController {

	@Autowired
	JobPositionService jobPositionService;

	@PostMapping("/")
	public ResponseEntity<?> addJobPositions(@RequestBody JobPositionRequest jobPositionRequest) throws Exception {
		return new ResponseEntity<>(jobPositionService.addJobPositions(jobPositionRequest), HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<?> getJobPositions() throws Exception {
		return new ResponseEntity<>(jobPositionService.getJobPositions(), HttpStatus.OK);
	}

	@DeleteMapping("/{employeeID}")
	public ResponseEntity<?> deleteJobPosition(@PathVariable Integer employeeID, @RequestParam Integer jobID)
			throws Exception {
		return new ResponseEntity<>(jobPositionService.deleteJobPosition(employeeID, jobID), HttpStatus.OK);
	}
	
	@PutMapping("/update/{employeeID}")
	public ResponseEntity<?> updateJobPosition(@PathVariable Integer employeeID,@RequestBody JobPositionRequest jobPositionRequest) throws Exception {
		return new ResponseEntity<>(jobPositionService.updateJobPosition(employeeID,jobPositionRequest), HttpStatus.OK);
	}
}
