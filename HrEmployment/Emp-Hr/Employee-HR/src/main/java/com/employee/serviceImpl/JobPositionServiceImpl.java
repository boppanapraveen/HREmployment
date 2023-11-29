package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.bean.JobPositionRequest;
import com.employee.bean.ResponseBean;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.JobPosition;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.JobPositionRepo;
import com.employee.service.JobPositionService;

@Service
public class JobPositionServiceImpl implements JobPositionService {

	@Autowired
	JobPositionRepo jobPositionRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public ResponseBean addJobPositions(JobPositionRequest jobRequest) throws Exception {
		try {
			Employee employee = employeeRepo.findById(jobRequest.getEmpID()).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee, please check employee ID"));
			}
			JobPosition jobPosition = new JobPosition();
			jobPosition.setDescription(jobRequest.getDescription());
			jobPosition.setJobTitle(jobRequest.getJobTitle());
			jobPosition.setSalaryRange(jobRequest.getSalaryRange());

			ResponseBean responseBean = new ResponseBean();
			responseBean.setEmployeeID(jobRequest.getEmpID());
			responseBean.setStatus("Job Position OpenedBy " + jobRequest.getEmpID());
			jobPositionRepo.save(jobPosition);

			return responseBean;

		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Adding Job Position"));
		}
	}

	@Override
	public List<JobPosition> getJobPositions() throws Exception {
		try {
			List<JobPosition> jobPositions = jobPositionRepo.findAll();
			if (CollectionUtils.isEmpty(jobPositions)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Job Position Table Is Empty"));
			}
			return jobPositions;
			
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching Job Position"));
		}
	}

	@Override
	public ResponseBean deleteJobPosition(Integer employeeID,Integer jobID) throws Exception {
		try {
			Employee employee = employeeRepo.findById(employeeID).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee, please check employee ID"));
			}
			jobPositionRepo.deleteById(jobID);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setEmployeeID(employeeID);
			responseBean.setStatus("Deleted SuccessFully");
			
			return responseBean;
			
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Deleting Job Position"));
		}
	}

	@Override
	public JobPosition updateJobPosition(Integer jobPositionID,JobPositionRequest jobRequest) throws Exception {
		try {
			Employee employee = employeeRepo.findById(jobRequest.getEmpID()).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee, please check employee ID"));
			}
			Optional<JobPosition> optional = jobPositionRepo.findById(jobPositionID);
			JobPosition jobPosition = optional.get();
			jobPosition.setDescription(jobRequest.getDescription());
			jobPosition.setJobTitle(jobRequest.getJobTitle());
			jobPosition.setSalaryRange(jobRequest.getSalaryRange());
			JobPosition updatedJobPosition = jobPositionRepo.save(jobPosition);
			return updatedJobPosition;
		}  catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Updating Job Position"));
		}
	}
	
	

}
