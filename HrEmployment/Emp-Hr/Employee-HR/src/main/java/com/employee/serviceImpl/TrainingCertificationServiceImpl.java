package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.CertificationReq;
import com.employee.bean.MessageResponse;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.TrainingCertification;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.TrainingCertificationsRepo;
import com.employee.service.TrainingCertificationService;

@Service
public class TrainingCertificationServiceImpl implements TrainingCertificationService{
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	TrainingCertificationsRepo trainingCertificationsRepo;

	@Override
	public TrainingCertification addCertification(CertificationReq certificationReq) throws Exception {
		try {
			Optional<Employee> employee = employeeRepo.findById(certificationReq.getHrEmpID());
			if (employee.isEmpty() || !employee.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			TrainingCertification trainingCertification = new TrainingCertification();
			trainingCertification.setCompletionDate(certificationReq.getCompletionDate());
			trainingCertification.setExpirationDate(certificationReq.getExpirationDate());
			trainingCertification.setTrainingName(certificationReq.getTrainingName());
			TrainingCertification savedTraining = trainingCertificationsRepo.save(trainingCertification);
			return savedTraining;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Creating entry in Certification Table"));
		}
	}

	@Override
	public TrainingCertification updateCertification(Integer trainingID,CertificationReq certificationReq) throws Exception {
		try {
			Optional<TrainingCertification> existingCertification = trainingCertificationsRepo.findById(trainingID);
			if(existingCertification.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "For the trainingID " + trainingID + " entry not found"));
			}
			Optional<Employee> employee = employeeRepo.findById(certificationReq.getHrEmpID());
			if (employee.isEmpty() || !employee.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			TrainingCertification trainingCertification = existingCertification.get();
			trainingCertification.setCompletionDate(certificationReq.getCompletionDate());
			trainingCertification.setExpirationDate(certificationReq.getExpirationDate());
			trainingCertification.setTrainingName(certificationReq.getTrainingName());
			TrainingCertification updatedCertification = trainingCertificationsRepo.save(trainingCertification);
			return updatedCertification;
		}catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while updating entry in Certification Table"));
		}
	}

	@Override
	public List<TrainingCertification> fetchAllCertifications() throws Exception {
		try {
			List<TrainingCertification> taCertifications = trainingCertificationsRepo.findAll();
			if (taCertifications.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "TrainingCertifications Table is Empty"));
			}
			return taCertifications;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while fetching entry in Certification Table"));
		}
	}

	@Override
	public MessageResponse deleteCertification(Integer trainingID,Integer hrEmpID) throws Exception {
		try {
			Optional<TrainingCertification> existingCertification = trainingCertificationsRepo.findById(trainingID);
			if(existingCertification.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "For the trainingID " + trainingID + " entry not found"));
			}
			Optional<Employee> employee = employeeRepo.findById(hrEmpID);
			if (employee.isEmpty() || !employee.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			trainingCertificationsRepo.deleteById(trainingID);
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setMessage("Delete SuccessFully");
			return messageResponse;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while deleting entry in Certification Table"));
		}
	}

	@Override
	public Employee mapCertificationToEmployee(Integer empID,Integer trainingID,Integer hrEmpID) throws Exception {
		try {
			
			Optional<Employee> optional = employeeRepo.findById(empID);
			if(optional.isEmpty() || optional.get().getActive().equals(0)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "For the employeeID " + empID + " entry not found"));
			}
			Employee employee = optional.get();
			Optional<Employee> optional2 = employeeRepo.findById(hrEmpID);
			if (optional2.isEmpty() || !optional2.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			Optional<TrainingCertification> existingCertification = trainingCertificationsRepo.findById(trainingID);
			if(existingCertification.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(
						HttpStatus.NOT_FOUND.name(), "For the trainingID " + trainingID + " entry not found"));
			}
			employee.setTrainingCertification(existingCertification.get());
			Employee updatedEmployee = employeeRepo.save(employee);
			return updatedEmployee;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while maping Certification To Employee"));
		}
	}

}
