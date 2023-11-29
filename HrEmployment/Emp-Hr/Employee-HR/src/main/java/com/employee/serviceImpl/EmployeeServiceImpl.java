package com.employee.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.bean.AuthBean;
import com.employee.bean.AuthVerified;
import com.employee.bean.EmployeeBean;
import com.employee.bean.MessageResponse;
import com.employee.bean.ResponseBean;
import com.employee.bean.TerminateRequest;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.model.EmployeeTerminations;
import com.employee.model.JobPosition;
import com.employee.model.Recruitment;
import com.employee.repository.DepartmentRepo;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.JobPositionRepo;
import com.employee.repository.RecruitmentRepo;
import com.employee.repository.TerminationRepo;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	JobPositionRepo jobPositionRepo;
	
	@Autowired
	RecruitmentRepo recruitmentRepo;
	
	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	TerminationRepo terminationRepo;
	
	ExampleMatcher modelMatcher = ExampleMatcher.matching()
			  .withIgnorePaths("empID") 
			  .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
			  .withMatcher("dateOfBirth", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
			  .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.ignoreCase());
	
	@Override
	public ResponseBean registerEmployee(EmployeeBean employeeBean) throws Exception {
		try {
			Employee employee = new Employee();
			employee.setAddress(employeeBean.getAddress());
			employee.setDateOfBirth(employeeBean.getDateOfBirth());
			employee.setDateOfJoining(employeeBean.getDateOfJoining());
			employee.setEmail(employeeBean.getEmail());
			employee.setFirstName(employeeBean.getFirstName());
			employee.setGender(employeeBean.getGender());
			employee.setLastName(employeeBean.getLastName());
			employee.setPhone(employeeBean.getPhone());
			employee.setRole(employeeBean.getRole());
			Department department = departmentRepo.findByDepartmentName(employeeBean.getDeptName());
			JobPosition jobPosition = jobPositionRepo.findByJobTitle(employeeBean.getJobTitle());
			Recruitment recruitment = recruitmentRepo.findByCandidateNameAndStatus(employeeBean.getFirstName() +" " + employeeBean.getLastName(), "Selected");
			employee.setDepartment(department);
			employee.setRecruitment(recruitment);
			employee.setJobPosition(jobPosition);
			employee.setActive(1);
			employee.setPassword(employeeBean.getPassword());
			Example<Employee> example = Example.of(employee, modelMatcher);
			boolean exists = employeeRepo.exists(example);
			if(exists) {
				throw new EmployeeHRException(HttpStatus.CONFLICT,
						new ErrorResponse(HttpStatus.CONFLICT.name(), "Employee Already Exists"));
			}
			Employee savedEmployee = employeeRepo.save(employee);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setEmployeeID(savedEmployee.getEmpID());
			responseBean.setStatus("Created SuccessFully");
			return responseBean;

		} catch (EmployeeHRException e) {
			throw e;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Registering an Employee"));
		}
	}

	@Override
	public Employee fetchEmployeeByEmployeeID(Integer employeeID) throws Exception {

		try {
			Employee employee = getEmployeeModel(employeeID);
			return employee;

		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching an Employee"));
		}
	}

	@Override
	public Employee getEmployeeModel(Integer employeeID) throws Exception {
		Optional<Employee> employee = employeeRepo.findById(employeeID);
		if (employee.isEmpty() || employee.get().getActive() == 0) {
			throw new EmployeeHRException(HttpStatus.NOT_FOUND,
					new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again, Employee might have terminated"));
		}
		return employee.get();
	}

	@Override
	public List<Employee> fetchAllEmployees(Integer employeeID) throws Exception {
		try {
			Employee employee = employeeRepo.findById(employeeID).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again, Only HR Has Permission"));
			}
			List<Employee> employees = employeeRepo.findAll();
			if(CollectionUtils.isEmpty(employees)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Employee Table Is Empty"));
			}
			return employees;
			
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching Employees List"));
		}
	}

	@Override
	public MessageResponse terminateEmployee(TerminateRequest req) throws Exception {
		try {
			Optional<Employee> hr = employeeRepo.findById(req.getHrEmpID());
			if (hr.isEmpty()|| !hr.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again, Only HR Has Permission"));
			}
			Optional<Employee> employee = employeeRepo.findById(req.getEmpId());
			if (employee.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check EmployeeID you want to terminate"));
			}
			EmployeeTerminations employeeTerminations = new EmployeeTerminations();
			employeeTerminations.setEmployee(employee.get());
			employeeTerminations.setReaseon(req.getReaseon());
			employeeTerminations.setTerminationDate(getCurrentDateAsString());
			terminationRepo.save(employeeTerminations);
			Employee updatEmployee = employee.get();
			updatEmployee.setActive(0);
			Employee savedEmployee = employeeRepo.save(updatEmployee);
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setMessage("Terminated SuccessFully");
			return messageResponse;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching Employees List"));
		}

	}
	
	private String getCurrentDateAsString() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}

	@Override
	public AuthVerified authenticateEmployee(AuthBean authBean) throws Exception {
		try {
			Employee employee = employeeRepo.findByEmailAndActive(authBean.getEmail(), 1);
			if (employee==null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID and Password Once Again "));
			}
			if(employee.getPassword().equals(authBean.getPassword())) {
				return new AuthVerified("Login Successfull",employee.getRole(),employee.getEmpID(),employee.getFirstName(),employee.getLastName());
			}
			return new AuthVerified("Login Failed",null,employee.getEmpID(),employee.getFirstName(),employee.getLastName());
		}catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while authenticating Employee"));
		}
	}

	@Override
	public Employee updateEmployee(EmployeeBean employeeBean,Integer empID) throws Exception {
		try {
			Optional<Employee> optional = employeeRepo.findById(empID);
			if (optional.isEmpty()|| optional.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Please Check Your Employee ID Once Again"));
			}
			Employee employee = optional.get();
			employee.setAddress(employeeBean.getAddress());
			employee.setDateOfBirth(employeeBean.getDateOfBirth());
			employee.setDateOfJoining(employeeBean.getDateOfJoining());
			employee.setEmail(employeeBean.getEmail());
			employee.setFirstName(employeeBean.getFirstName());
			employee.setGender(employeeBean.getGender());
			employee.setLastName(employeeBean.getLastName());
			employee.setPhone(employeeBean.getPhone());
			employee.setRole(employeeBean.getRole());
			Department department = departmentRepo.findByDepartmentName(employeeBean.getDeptName());
			JobPosition jobPosition = jobPositionRepo.findByJobTitle(employeeBean.getJobTitle());
			Recruitment recruitment = recruitmentRepo.findByCandidateNameAndStatus(employeeBean.getFirstName() +" " + employeeBean.getLastName(), "Selected");
			employee.setDepartment(department);
			employee.setRecruitment(recruitment);
			employee.setJobPosition(jobPosition);
			employee.setPassword(employeeBean.getPassword());
			Employee savedEmployee = employeeRepo.save(employee);

			return savedEmployee;

		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while updating an Employee"));
		}
	}
	
	

}
