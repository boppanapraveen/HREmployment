package com.employee.service;

import java.util.List;

import com.employee.bean.DeductionBean;
import com.employee.model.PayrollDeductions;

public interface PayrollDeductionService {
	
	public List<PayrollDeductions> fetchPayrollDeductions(Integer employeeID) throws Exception;
	
	public List<PayrollDeductions> fetchPayrollDeductionsList(Integer employeeID) throws Exception;
	
	public PayrollDeductions addDeductions(DeductionBean deductionBean) throws Exception;
	
	public PayrollDeductions updatePayrollDeductions(Integer deductionID,DeductionBean deductionBean) throws Exception;

}
