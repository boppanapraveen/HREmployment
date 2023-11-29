package com.employee.service;

import java.util.List;

import com.employee.bean.EmergencyContactsReq;
import com.employee.bean.MessageResponse;
import com.employee.model.EmployeeEmergencyContacts;

public interface EmployeeEmergencyContactsService {
	
	public EmployeeEmergencyContacts addEmergencyContacts(EmergencyContactsReq emergencyContactsReq) throws Exception;
	
	public List<EmployeeEmergencyContacts> fetchEmergencyContacts(Integer empID) throws Exception;
	
	public MessageResponse deleteEmergencyContacts(Integer id) throws Exception;
	
	public EmployeeEmergencyContacts updatEmergencyContacts(Integer id,EmergencyContactsReq emergencyContactsReq) throws Exception;

}
