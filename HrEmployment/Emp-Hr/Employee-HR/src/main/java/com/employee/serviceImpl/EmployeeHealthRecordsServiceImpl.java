package com.employee.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.HealthRecordRequest;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.HealthRecord;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.HealthRecordRepo;
import com.employee.service.EmployeeHealthRecordsService;

@Service
public class EmployeeHealthRecordsServiceImpl implements EmployeeHealthRecordsService{

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	HealthRecordRepo healthRecordRepo;
	
	@Override
	public HealthRecord addHealthRecord(HealthRecordRequest recordRequest) throws Exception {
		try {
			Optional<Employee> employee = employeeRepo.findById(recordRequest.getEmpID());
			if(employee.isEmpty() || employee.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			HealthRecord healthRecord = new HealthRecord();
			healthRecord.setAllergies(recordRequest.getAllergies());
			healthRecord.setEmployee(employee.get());
			healthRecord.setField(recordRequest.getField());
			healthRecord.setMedicalCondition(recordRequest.getMedicalCondition());
			healthRecord.setMedications(recordRequest.getMedications());
			HealthRecord savedHealthRecord = healthRecordRepo.save(healthRecord);
			return savedHealthRecord;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while saving HealthRecords"));
		}
	}

	@Override
	public HealthRecord fetcHealthRecord(Integer empID) throws Exception {
		try {
			HealthRecord healthRecord = healthRecordRepo.findByEmployeeEmpIDAndEmployeeActive(empID, 1);
			if(healthRecord==null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			return healthRecord;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching HealthRecords"));
		}
	}

}
