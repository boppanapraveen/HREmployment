package com.employee.service;

import java.util.List;

import com.employee.bean.ResponseBean;
import com.employee.bean.SalaryRecordRequest;
import com.employee.model.SalaryRecord;

public interface SalaryRecordService {

	public ResponseBean addSalaryRecord(SalaryRecordRequest salaryRequest) throws Exception;

	public List<SalaryRecord> getSalaryRecordByEmployeeID(Integer EmployeeID) throws Exception;

}
