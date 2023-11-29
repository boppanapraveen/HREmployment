package com.employee.service;

import com.employee.bean.HealthRecordRequest;
import com.employee.model.HealthRecord;

public interface EmployeeHealthRecordsService {
	public HealthRecord addHealthRecord(HealthRecordRequest recordRequest) throws Exception;
	public HealthRecord fetcHealthRecord(Integer empID) throws Exception;
}
