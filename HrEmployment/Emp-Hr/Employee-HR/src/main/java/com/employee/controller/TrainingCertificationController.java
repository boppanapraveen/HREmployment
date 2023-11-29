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

import com.employee.bean.CertificationReq;
import com.employee.service.TrainingCertificationService;

@RestController
@RequestMapping("/api/certification")
public class TrainingCertificationController {
	
	@Autowired
	TrainingCertificationService trainingCertificationService;
	
    @PostMapping("/")
    public ResponseEntity<?> addCertification(@RequestBody CertificationReq certificationReq) throws Exception {
        return new ResponseEntity<>(trainingCertificationService.addCertification(certificationReq),HttpStatus.CREATED);
    }
    
    @GetMapping("/")
    public ResponseEntity<?> fetchAllCertifications() throws Exception {
        return new ResponseEntity<>(trainingCertificationService.fetchAllCertifications(),HttpStatus.OK);
    }
    
    @PutMapping("/{trainingID}")
    public ResponseEntity<?> updateCertification(@PathVariable Integer trainingID,@RequestBody CertificationReq certificationReq) throws Exception {
        return new ResponseEntity<>(trainingCertificationService.updateCertification(trainingID,certificationReq),HttpStatus.OK);
    }
    
    @DeleteMapping("/{trainingID}")
    public ResponseEntity<?> deleteCertification(@PathVariable Integer trainingID,@RequestParam Integer hrEmpID) throws Exception {
        return new ResponseEntity<>(trainingCertificationService.deleteCertification(trainingID,hrEmpID),HttpStatus.OK);
    }

    @PutMapping("/map/{trainingID}")
    public ResponseEntity<?> mapCertificationToEmployee(@PathVariable Integer trainingID,@RequestParam Integer empID,@RequestParam Integer hrEmpID) throws Exception {
        return new ResponseEntity<>(trainingCertificationService.mapCertificationToEmployee(empID,trainingID,hrEmpID),HttpStatus.OK);
    }
}
