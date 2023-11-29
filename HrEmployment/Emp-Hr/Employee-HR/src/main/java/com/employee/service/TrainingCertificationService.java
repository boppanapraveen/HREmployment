package com.employee.service;

import java.util.List;

import com.employee.bean.CertificationReq;
import com.employee.bean.MessageResponse;
import com.employee.model.Employee;
import com.employee.model.TrainingCertification;

public interface TrainingCertificationService {
	
	public TrainingCertification addCertification(CertificationReq certificationReq) throws Exception;
	public TrainingCertification updateCertification(Integer trainingID,CertificationReq certificationReq) throws Exception;
	public List<TrainingCertification> fetchAllCertifications() throws Exception;
	public MessageResponse deleteCertification(Integer trainingID,Integer hrEmpID) throws Exception;
	public Employee mapCertificationToEmployee(Integer empID,Integer trainingID,Integer hrEmpID) throws Exception;
	
}
