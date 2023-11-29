package com.employee.service;

import java.util.List;

import com.employee.bean.AttendenceBean;
import com.employee.bean.ResponseBean;
import com.employee.model.Attendance;
import com.employee.model.TimeSheet;

public interface AttendenceService {
	
	public Attendance addAttendence(Integer employeeID) throws Exception;
	
    public List<Attendance> getAttendancesForEmployee(Integer empID) throws Exception;
    
    public Attendance punchOutAttendance(String date,Integer employeeID) throws Exception;
    
    public List<TimeSheet> fetchTimeSheet(Integer empID) throws Exception;
    

}
