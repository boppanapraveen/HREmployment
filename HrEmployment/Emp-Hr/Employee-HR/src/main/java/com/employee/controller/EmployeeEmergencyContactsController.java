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
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.EmergencyContactsReq;
import com.employee.service.EmployeeEmergencyContactsService;

@RestController
@RequestMapping("/api/contacts")
public class EmployeeEmergencyContactsController {
	
	@Autowired
	EmployeeEmergencyContactsService emergencyContactsService;
	

	@PostMapping("/")
	public ResponseEntity<?> addEmergencyContacts(@RequestBody EmergencyContactsReq emergencyContactsReq) throws Exception {
		return new ResponseEntity<>(emergencyContactsService.addEmergencyContacts(emergencyContactsReq), HttpStatus.CREATED);
	}

	@DeleteMapping("/{contactID}")
	public ResponseEntity<?> getJobPositions(@PathVariable Integer contactID) throws Exception {
		return new ResponseEntity<>(emergencyContactsService.deleteEmergencyContacts(contactID), HttpStatus.OK);
	}

	@GetMapping("/{employeeID}")
	public ResponseEntity<?> fetchEmergencyContacts(@PathVariable Integer employeeID)
			throws Exception {
		return new ResponseEntity<>(emergencyContactsService.fetchEmergencyContacts(employeeID), HttpStatus.OK);
	}
	
	@PutMapping("/update/{contactID}")
	public ResponseEntity<?> updatEmergencyContacts(@PathVariable Integer contactID,@RequestBody EmergencyContactsReq emergencyContactsReq) throws Exception {
		return new ResponseEntity<>(emergencyContactsService.updatEmergencyContacts(contactID,emergencyContactsReq), HttpStatus.OK);
	}
}
