package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.ProjectAssignmentsRequest;
import com.employee.bean.ProjectDetailsRequest;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.model.ProjectAssignments;
import com.employee.model.ProjectDetails;
import com.employee.repository.DepartmentRepo;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.ProjectAssignmentsRepo;
import com.employee.repository.ProjectDetailsRepo;
import com.employee.service.ProjectDetailsService;
@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	ProjectDetailsRepo projectDetailsRepo;
	
	@Autowired
	ProjectAssignmentsRepo projectAssignmentsRepo;

	@Override
	public ProjectDetails addProjectDetails(ProjectDetailsRequest request) throws Exception {
		try {
			Employee employee = employeeRepo.findById(request.getEmpID()).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"you are not HR, please check employee ID"));
			}
			Optional<Department> optionalDepartment = departmentRepo.findById(request.getDeptID());
			if(optionalDepartment.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Department does not exists"));
			}
			ProjectDetails projectDetails = new ProjectDetails();
			projectDetails.setDepartment(optionalDepartment.get());
			projectDetails.setDescription(request.getDescription());
			projectDetails.setEndDate(request.getEndDate());
			projectDetails.setProjectName(request.getProjectName());
			projectDetails.setStartDate(request.getStartDate());
			ProjectDetails saveDetails = projectDetailsRepo.save(projectDetails);
			return saveDetails;
		}  catch (EmployeeHRException e) {
			throw e;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Saving ProjectDetails"));
		}
	}

	@Override
	public List<ProjectDetails> fetchProjectDetailsBasedOnDepartment(Integer deptID) throws Exception {
		try {
			List<ProjectDetails> projectDetails = projectDetailsRepo.findByDepartmentDeptID(deptID);
			if(projectDetails.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"No Records Found"));
			}
			return projectDetails;
		}catch (EmployeeHRException e) {
			throw e;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching ProjectDetails"));
		}

	}

	@Override
	public ProjectAssignments addAssignmentsForTheProject(ProjectAssignmentsRequest request) throws Exception {
		try {
			Optional<Employee> employee = employeeRepo.findById(request.getHrEmpID());
			if (employee.isEmpty() || !employee.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"you are not HR, please check employee ID"));
			}
			Optional<Employee> optionalEmployee = employeeRepo.findById(request.getEmpID());
			if (optionalEmployee.isEmpty() || optionalEmployee.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Employee Not Found To Assign"));
			}
			ProjectAssignments projectAssignments = new ProjectAssignments();
			projectAssignments.setDescription(request.getDescription());
			projectAssignments.setEmployee(optionalEmployee.get());
			projectAssignments.setEndDate(request.getEndDate());
			projectAssignments.setStartDate(request.getStartDate());
			Optional<ProjectDetails> optional = projectDetailsRepo.findById(request.getProjectID());
			if(optional.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"ProjectDetails Not Found To Assign"));
			}
			projectAssignments.setProjectDetails(optional.get());
			return projectAssignmentsRepo.save(projectAssignments);
		} catch (EmployeeHRException e) {
			throw e;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while assigning assignments to Employees"));
		}
	}

	@Override
	public List<ProjectAssignments> fetchProjectAssignmentsBasedOnProject(Integer projectID,Integer empID) throws Exception {
		try {
			Optional<Employee> optionalEmployee = employeeRepo.findById(empID);
			if (optionalEmployee.isEmpty() || optionalEmployee.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Employee Not Found"));
			}
			List<ProjectAssignments> projectAssignments = projectAssignmentsRepo.findByProjectDetailsProjectIDAndEmployeeEmpID(projectID,empID);
			if (projectAssignments.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Assignments Not Found"));
			}
			return projectAssignments;
		} catch (EmployeeHRException e) {
			throw e;
		}catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching assignments"));
		}

	}

}
