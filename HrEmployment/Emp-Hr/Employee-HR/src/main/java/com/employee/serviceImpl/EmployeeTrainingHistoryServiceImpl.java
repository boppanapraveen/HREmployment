package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.EmpTrainingHistoryRequest;
import com.employee.bean.MessageResponse;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.EmployeeTrainingHistory;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.EmployeeTrainingHistoryRepo;
import com.employee.service.EmployeeTrainingHistoryService;

@Service
public class EmployeeTrainingHistoryServiceImpl implements EmployeeTrainingHistoryService{
	
	@Autowired
	EmployeeTrainingHistoryRepo employeeTrainingHistoryRepo;

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Override
	public EmployeeTrainingHistory assignTrainings(EmpTrainingHistoryRequest empTrainingHistoryRequest) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Employee> hrOptional = employeeRepo.findById(empTrainingHistoryRequest.getHrEmpID());
			if(hrOptional.isEmpty() || !hrOptional.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "You Are Not Authorized to do"));
			}
			EmployeeTrainingHistory employeeTrainingHistory = new EmployeeTrainingHistory();
			employeeTrainingHistory.setTrainingDate(empTrainingHistoryRequest.getTrainingDate());
			employeeTrainingHistory.setTrainingName(empTrainingHistoryRequest.getTrainingName());
			Optional<Employee> employee = employeeRepo.findById(empTrainingHistoryRequest.getEmpID());
			if(employee.isEmpty() || employee.get().getActive() == 0) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Employee Not Found"));
			}
			employeeTrainingHistory.setEmployee(employee.get());
			EmployeeTrainingHistory savedEmployeeTrainingHistory = employeeTrainingHistoryRepo.save(employeeTrainingHistory);
			return savedEmployeeTrainingHistory;
		}catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while saving EmployeeTrainingHistory"));
		}
	}

	@Override
	public List<EmployeeTrainingHistory> fetchAssignedTrainings(Integer empID) throws Exception {
		try {
			
			List<EmployeeTrainingHistory> employeeTrainingHistories = employeeTrainingHistoryRepo.findByEmployeeEmpIDAndEmployeeActive(empID,1);
			if(employeeTrainingHistories.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "Whether EmployeeID is inCorrect or There are no trainings Assigned to the employeeID " + empID));
			}
			return employeeTrainingHistories;
			
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching EmployeeTrainingHistory"));
		}

	}

	@Override
	public MessageResponse markCompletedTrainings(Integer trainingID,Integer hrEmpID) throws Exception {
		try {
			Optional<Employee> hrOptional = employeeRepo.findById(hrEmpID);
			if(hrOptional.isEmpty() || hrOptional.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "You Are Not Authorized to do"));
			}
			Optional<EmployeeTrainingHistory> employeeTrainingHistory = employeeTrainingHistoryRepo.findById(trainingID);
			if(employeeTrainingHistory.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "For the trainingId there is no Record Found : " + trainingID));
			}
			employeeTrainingHistoryRepo.deleteById(trainingID);
			MessageResponse employeeTrainingHistoryResponse = new MessageResponse();
			employeeTrainingHistoryResponse.setMessage("Marked Completed");
			return employeeTrainingHistoryResponse;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching EmployeeTrainingHistory"));
		}
	}

}
