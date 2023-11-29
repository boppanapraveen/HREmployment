package com.employee.serviceImpl;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Attendance;
import com.employee.model.TimeSheet;
import com.employee.repository.AttendenceRepo;
import com.employee.repository.TimeSheetRepo;
import com.employee.service.AttendenceService;
import com.employee.service.EmployeeService;

@Service
public class AttendenceServiceImpl implements AttendenceService {

	@Autowired
	AttendenceRepo attendenceRepo;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	TimeSheetRepo timeSheetRepo;

	@Override
	public Attendance addAttendence(Integer employeeID) throws Exception {
		try {
			Attendance attendance = new Attendance();
			attendance.setDate(getCurrentDateAsString());
	        long currentTimeMillis = System.currentTimeMillis();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date = new Date(currentTimeMillis);
	        String formattedDate = sdf.format(date);
			attendance.setClockInTime(formattedDate);
			attendance.setEmployee(employeeService.getEmployeeModel(employeeID));
			Attendance savedAttendance = attendenceRepo.save(attendance);
			return savedAttendance;

		} catch (EmployeeHRException e) {
			throw e;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while punching Attendece"));
		}
	}

	private String getCurrentDateAsString() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}

	@Override
	public List<Attendance> getAttendancesForEmployee(Integer empID) throws Exception {
		// TODO Auto-generated method stub
		try {
			List<Attendance> list = attendenceRepo.findByEmployeeEmpIDAndEmployeeActive(empID,1);
			if (CollectionUtils.isEmpty(list)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again"));
			}
			return list;

		}catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching attendence Details"));
		}
	}

	@Override
	public Attendance punchOutAttendance(String date,Integer employeeID) throws Exception {
		
		try {
			Attendance attendance = attendenceRepo.findByDateAndEmployeeEmpIDAndEmployeeActive(date, employeeID,1);
			if(attendance == null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again"));
			}
	        long currentTimeMillis = System.currentTimeMillis();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date dt = new Date(currentTimeMillis);
	        String formattedDate = sdf.format(dt);
			attendance.setClockOutTime(formattedDate);
			TimeSheet timeSheet = new TimeSheet();
			timeSheet.setDate(date);
			
            Date parsedDate1 = sdf.parse(attendance.getClockInTime());
            Date parsedDate2 = sdf.parse(attendance.getClockOutTime());

            long timeDifferenceMillis = parsedDate2.getTime() - parsedDate1.getTime();
	        long hours = timeDifferenceMillis / (60 * 60 * 1000);
			timeSheet.setHoursWorked(hours);
			timeSheet.setEmployee(attendance.getEmployee());
			timeSheetRepo.save(timeSheet);
			Attendance updatedAttendance = attendenceRepo.save(attendance);

			return updatedAttendance;
		}  catch (EmployeeHRException e) {
			throw e;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while updating Attendece"));
		}
	}

	@Override
	public List<TimeSheet> fetchTimeSheet(Integer empID) throws Exception {
		try {
			List<TimeSheet> timeSheet = timeSheetRepo.findByEmployeeEmpIDAndEmployeeActive(empID,1);
			if(CollectionUtils.isEmpty(timeSheet)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again"));
			}
			return timeSheet;
		} catch (EmployeeHRException e) {
			throw e;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching TimeSheet"));
		}
	}

}
