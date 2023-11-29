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

import com.employee.bean.RecruitmentRequest;
import com.employee.bean.RecruitmentUpdateReq;
import com.employee.service.RecruitmentService;

@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentController {

	@Autowired
	RecruitmentService recruitmentService;

	@PostMapping("/")
	public ResponseEntity<?> addRecruitmentDetails(@RequestBody RecruitmentRequest recruitmentRequest)
			throws Exception {
		return new ResponseEntity<>(recruitmentService.addRecruitmentDetails(recruitmentRequest), HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateStatusRecruitment(@RequestBody RecruitmentUpdateReq recruitmentUpdateReq)
			throws Exception {
		return new ResponseEntity<>(recruitmentService.updateStatusRecruitment(recruitmentUpdateReq), HttpStatus.OK);
	}

	@GetMapping("/{recruitmentID}")
	public ResponseEntity<?> getAllLeaveRequest(@PathVariable Integer recruitmentID) throws Exception {
		return new ResponseEntity<>(recruitmentService.fetchRecruitment(recruitmentID), HttpStatus.OK);
	}
	
	@PutMapping("/{recruitmentID}")
	public ResponseEntity<?> updateRecruitment(@PathVariable Integer recruitmentID,@RequestBody RecruitmentRequest recruitmentRequest) throws Exception {
		return new ResponseEntity<>(recruitmentService.updateRecruitment(recruitmentID,recruitmentRequest), HttpStatus.OK);
	}
	
	@GetMapping("/{empID}/all")
	public ResponseEntity<?> fetchAllRecruitments(@PathVariable Integer empID) throws Exception {
		return new ResponseEntity<>(recruitmentService.fetchAllRecruitments(empID), HttpStatus.OK);
	}

}
