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

import com.employee.service.AttendenceService;

@RestController
@RequestMapping("/api/attendence")
public class AttendenceController {
	
	@Autowired
	AttendenceService attendenceService;
	
    @PostMapping("/")
    public ResponseEntity<?> addAttendence(@RequestParam Integer employeeID) throws Exception {
        return new ResponseEntity<>(attendenceService.addAttendence(employeeID),HttpStatus.CREATED);
    }
    
    @GetMapping("/{employeeID}")
    public ResponseEntity<?> getAttendancesForEmployee(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(attendenceService.getAttendancesForEmployee(employeeID),HttpStatus.OK);
    }
    
    @PutMapping("/{employeeID}")
    public ResponseEntity<?> punchOutAttendance(@RequestParam String date,@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(attendenceService.punchOutAttendance(date,employeeID),HttpStatus.OK);
    }
    
    @GetMapping("/timesheet/{employeeID}")
    public ResponseEntity<?> fetchTimeSheet(@PathVariable Integer employeeID) throws Exception {
        return new ResponseEntity<>(attendenceService.fetchTimeSheet(employeeID),HttpStatus.OK);
    }
}
