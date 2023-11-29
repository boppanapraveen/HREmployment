package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.ProjectAssignmentsRequest;
import com.employee.bean.ProjectDetailsRequest;
import com.employee.service.ProjectDetailsService;

@RestController
@RequestMapping("/api/project")
public class ProjectDetailsController {
	
	@Autowired
	ProjectDetailsService projectDetailsService;
	
    @PostMapping("/")
    public ResponseEntity<?> addProjectDetails(@RequestBody ProjectDetailsRequest request) throws Exception {
        return new ResponseEntity<>(projectDetailsService.addProjectDetails(request),HttpStatus.CREATED);
    }
    
    @GetMapping("/{deptID}")
    public ResponseEntity<?> fetchProjectDetailsBasedOnDepartment(@PathVariable Integer deptID) throws Exception {
        return new ResponseEntity<>(projectDetailsService.fetchProjectDetailsBasedOnDepartment(deptID),HttpStatus.OK);
    }
    
    @PostMapping("/assign/")
    public ResponseEntity<?> addAssignmentsForTheProject(@RequestBody ProjectAssignmentsRequest request) throws Exception {
        return new ResponseEntity<>(projectDetailsService.addAssignmentsForTheProject(request),HttpStatus.CREATED);
    }
    
    @GetMapping("/assign/{empID}")
    public ResponseEntity<?> fetchProjectAssignmentsBasedOnProject(@PathVariable Integer empID,@RequestParam Integer projectID) throws Exception {
        return new ResponseEntity<>(projectDetailsService.fetchProjectAssignmentsBasedOnProject(projectID,empID),HttpStatus.OK);
    }

}
