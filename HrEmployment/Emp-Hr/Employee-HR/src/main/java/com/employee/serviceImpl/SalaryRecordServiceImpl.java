package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.bean.ResponseBean;
import com.employee.bean.SalaryRecordRequest;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.PayrollDeductions;
import com.employee.model.SalaryRecord;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.PayrollDeductionRepo;
import com.employee.repository.SalaryRecordRepo;
import com.employee.service.SalaryRecordService;

@Service
public class SalaryRecordServiceImpl implements SalaryRecordService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	SalaryRecordRepo salaryRecordRepo;

	@Autowired
	PayrollDeductionRepo payrollDeductionRepo;

	@Override
	public ResponseBean addSalaryRecord(SalaryRecordRequest salaryRequest) throws Exception {
		try {

			SalaryRecord salaryRecord = new SalaryRecord();
			Optional<Employee> employee = employeeRepo.findById(salaryRequest.getEmployeeID());
			if (employee.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again"));
			}
			salaryRecord.setEmployee(employee.get());
			salaryRecord.setEndDate(salaryRequest.getEndDate());
			salaryRecord.setMonth(salaryRequest.getMonth());
			salaryRecord.setSalary(salaryRequest.getSalary());
			salaryRecord.setStartDate(salaryRequest.getStartDate());

			Optional<Employee> hrEmployee = employeeRepo.findById(salaryRequest.getHrEmployeeID());
			if (!hrEmployee.isEmpty() || hrEmployee.get().getRole().equals("HR")) {
				salaryRecordRepo.save(salaryRecord);
				ResponseBean responseBean = new ResponseBean();
				responseBean.setEmployeeID(salaryRequest.getEmployeeID());
				responseBean.setStatus("Added Record");
				return responseBean;
			} else {
				throw new EmployeeHRException(HttpStatus.BAD_REQUEST, new ErrorResponse(HttpStatus.BAD_REQUEST.name(),
						"Please Check Your Employee ID Once Again, You are not Autorized to do"));
			}

		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Adding Salary Record"));
		}
	}

	@Override
	public List<SalaryRecord> getSalaryRecordByEmployeeID(Integer EmployeeID) throws Exception {
		try {
			List<SalaryRecord> salaryRecord = salaryRecordRepo.fetchSalaryRecords(EmployeeID);
			if (CollectionUtils.isEmpty(salaryRecord)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again"));
			}
			return salaryRecord;

		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching Salary Record"));
		}
	}


}
