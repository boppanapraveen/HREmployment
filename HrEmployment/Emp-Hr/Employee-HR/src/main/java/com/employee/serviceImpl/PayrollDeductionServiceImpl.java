package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.bean.DeductionBean;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.PayrollDeductions;
import com.employee.model.SalaryRecord;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.PayrollDeductionRepo;
import com.employee.repository.SalaryRecordRepo;
import com.employee.service.PayrollDeductionService;

@Service
public class PayrollDeductionServiceImpl implements PayrollDeductionService{
	
	@Autowired
	PayrollDeductionRepo payrollDeductionRepo;
	
	@Autowired 
	EmployeeRepo employeeRepo;
	
	@Autowired
	SalaryRecordRepo salaryRecordRepo;


	@Override
	public List<PayrollDeductions> fetchPayrollDeductions(Integer employeeID) throws Exception {
		try {
			List<PayrollDeductions> payrollDeductions = payrollDeductionRepo.findByEmployeeID(employeeID);
			if(CollectionUtils.isEmpty(payrollDeductions)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "No Entry Found"));
			}
			return payrollDeductions;
		}catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching Payroll Deductions"));
		}
	}

	@Override
	public List<PayrollDeductions> fetchPayrollDeductionsList(Integer employeeID) throws Exception {
		try {
			Optional<Employee> employee = employeeRepo.findById(employeeID);
			if(employee.isEmpty() || !employee.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.BAD_REQUEST,
						new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "Please Check Your Employee ID Once Again, Because You Are Not Authorized"));
			}
			List<PayrollDeductions> payrollDeductions = payrollDeductionRepo.findAll();
			if(CollectionUtils.isEmpty(payrollDeductions)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "No Entry Found"));
			}
			return payrollDeductions;
		}catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching Payroll Deductions"));
		}
	}

	@Override
	public PayrollDeductions addDeductions(DeductionBean deductionBean) throws Exception {
		try {
			Employee employee = employeeRepo.findById(deductionBean.getHrEmployeeID()).get();
			if(employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.BAD_REQUEST,
						new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "Please Check Your Employee ID Once Again"));
			}
			PayrollDeductions payrollDeductions =  new PayrollDeductions();
			payrollDeductions.setDeductionType(deductionBean.getDeductionType());
			payrollDeductions.setEffectiveDate(deductionBean.getEffectiveDate());
			payrollDeductions.setRate(deductionBean.getRate());
			payrollDeductions.setMonth(deductionBean.getMonth());
			SalaryRecord salaryRecord = salaryRecordRepo.findByMonthAndEmployeeEmpIDAndEmployeeActive(deductionBean.getMonth(),deductionBean.getEmployeeID(),1);
			if(salaryRecord == null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again,Because No Salary Record Found For that month"));
			}
			payrollDeductions.setSalaryRecord(salaryRecord);
			PayrollDeductions saveDeductions = payrollDeductionRepo.save(payrollDeductions);
			return saveDeductions;
		}catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching Payroll Deductions"));
		}
	}

	@Override
	public PayrollDeductions updatePayrollDeductions(Integer deductionID,DeductionBean deductionBean) throws Exception {
		try {
			Employee employee = employeeRepo.findById(deductionBean.getHrEmployeeID()).get();
			if(employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.BAD_REQUEST,
						new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "Please Check Your Employee ID Once Again"));
			}
			Optional<PayrollDeductions> payrollDeductionsOpt = payrollDeductionRepo.findById(deductionID);
			if(payrollDeductionsOpt.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your deduction ID"));
			}
			PayrollDeductions payrollDeductions = payrollDeductionsOpt.get();
			payrollDeductions.setDeductionType(deductionBean.getDeductionType());
			payrollDeductions.setEffectiveDate(deductionBean.getEffectiveDate());
			Integer oldRate = payrollDeductions.getRate();
			payrollDeductions.setRate(deductionBean.getRate());
			payrollDeductions.setMonth(deductionBean.getMonth());
			SalaryRecord salaryRecord = salaryRecordRepo.findByMonthAndEmployeeEmpIDAndEmployeeActive(deductionBean.getMonth(),deductionBean.getEmployeeID(),1);
			if(salaryRecord == null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again,Because No Salary Record Found For that month"));
			}
			Integer updatedSalary = (oldRate - deductionBean.getRate()) + salaryRecord.getSalary();
			salaryRecord.setSalary(updatedSalary);
			salaryRecordRepo.save(salaryRecord);
			payrollDeductions.setSalaryRecord(salaryRecord);
			PayrollDeductions saveDeductions = payrollDeductionRepo.save(payrollDeductions);
			return saveDeductions;
		}catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while updating Payroll Deductions"));
		}
	}

}
