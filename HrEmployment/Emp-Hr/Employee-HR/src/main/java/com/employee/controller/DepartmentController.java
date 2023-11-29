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

import com.employee.bean.DepartmentRequest;
import com.employee.service.DepartmentService;

@RestController
@RequestMapping("/api/dept")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@PostMapping("/")
	public ResponseEntity<?> addDepartment(@RequestBody DepartmentRequest departmentRequest) throws Exception {
		return new ResponseEntity<>(departmentService.addDepartment(departmentRequest), HttpStatus.CREATED);
	}

	@GetMapping("/{deptID}")
	public ResponseEntity<?> fetchDepartment(@PathVariable Integer deptID) throws Exception {
		return new ResponseEntity<>(departmentService.fetchDepartment(deptID), HttpStatus.OK);
	}

	@DeleteMapping("/{deptID}")
	public ResponseEntity<?> deleteDepartment(@PathVariable Integer deptID,@RequestParam Integer empID) throws Exception {
		return new ResponseEntity<>(departmentService.deleteDepartment(deptID,empID), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllDepartments() throws Exception {
		return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
	}
	
	@PutMapping("/update/{deptID}")
	public ResponseEntity<?> updateDepartment(@PathVariable Integer deptID,@RequestBody DepartmentRequest departmentRequest) throws Exception {
		return new ResponseEntity<>(departmentService.updateDepartment(deptID,departmentRequest), HttpStatus.CREATED);
	}
}
