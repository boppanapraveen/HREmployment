package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.bean.DepartmentRequest;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.repository.DepartmentRepo;
import com.employee.repository.EmployeeRepo;
import com.employee.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	ExampleMatcher modelMatcher = ExampleMatcher.matching()
			  .withIgnorePaths("deptID") 
			  .withMatcher("departmentName", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
			  .withMatcher("location", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
			  .withMatcher("budget", ExampleMatcher.GenericPropertyMatchers.ignoreCase());

	@Override
	public Department addDepartment(DepartmentRequest deptRequest) throws Exception {
		try {
			Employee employee = employeeRepo.findById(deptRequest.getEmployeeID()).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			Department department = new Department();
			department.setBudget(deptRequest.getBudget());
			department.setDepartmentName(deptRequest.getDepartmentName());
			department.setLocation(deptRequest.getLocation());
			Example<Department> example = Example.of(department, modelMatcher);
			boolean exists = departmentRepo.exists(example);
			if(exists) {
				throw new EmployeeHRException(HttpStatus.CONFLICT,
						new ErrorResponse(HttpStatus.CONFLICT.name(), "Department Already Exists"));
			}
			Department savedDepartment = departmentRepo.save(department);
			return savedDepartment;
		}  catch (EmployeeHRException e) {
			throw e;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Saving Department Details"));
		}
	}

	@Override
	public Department fetchDepartment(Integer deptID) throws Exception {
		try {
			Department department = departmentRepo.findById(deptID).get();
			if (department == null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Department ID is Incorrect Please Check "));
			}
			return department;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching Department Details"));
		}
	}

	@Override
	public String deleteDepartment(Integer deptID,Integer employeeID) throws Exception {
		try {
			Employee employee = employeeRepo.findById(employeeID).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee, please check employee ID"));
			}
			Optional<Department> department = departmentRepo.findById(deptID);
			if (department.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Department ID is Incorrect Please Check "));
			}
			departmentRepo.deleteById(deptID);
			return "Deleted Successfully";
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Deleting Department Details"));
		}
	}

	@Override
	public List<Department> getAllDepartments() throws Exception {
		try {
			List<Department> departments = departmentRepo.findAll();
			if(CollectionUtils.isEmpty(departments)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Department Table Is Empty "));
			}
			return departments;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching Department Details"));
		}
	}

	@Override
	public Department updateDepartment(Integer deptID, DepartmentRequest deptRequest) throws Exception {
		try {
			Employee employee = employeeRepo.findById(deptRequest.getEmployeeID()).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			Optional<Department> department = departmentRepo.findById(deptID);
			if (department.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Department ID is Incorrect Please Check "));
			}
			Department updateDepartment = department.get();
			updateDepartment.setBudget(deptRequest.getBudget());
			updateDepartment.setDepartmentName(deptRequest.getDepartmentName());
			updateDepartment.setLocation(deptRequest.getLocation());
			departmentRepo.save(updateDepartment);
			return updateDepartment;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Updatings Department Details"));
		}
	}

}
