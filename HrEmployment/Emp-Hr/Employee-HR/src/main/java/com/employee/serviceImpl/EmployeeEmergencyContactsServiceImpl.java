package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.bean.EmergencyContactsReq;
import com.employee.bean.MessageResponse;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.EmployeeEmergencyContacts;
import com.employee.repository.EmployeeEmergencyContactsRepo;
import com.employee.repository.EmployeeRepo;
import com.employee.service.EmployeeEmergencyContactsService;

@Service
public class EmployeeEmergencyContactsServiceImpl implements EmployeeEmergencyContactsService {

	@Autowired
	EmployeeEmergencyContactsRepo emergencyContactsRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public EmployeeEmergencyContacts addEmergencyContacts(EmergencyContactsReq emergencyContactsReq) throws Exception {
		try {
			Employee employee = employeeRepo.findById(emergencyContactsReq.getEmpID()).get();
			if (employee == null || employee.getActive() == 0 ) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			EmployeeEmergencyContacts emergencyContacts = new EmployeeEmergencyContacts();
			emergencyContacts.setContactNo(emergencyContactsReq.getContactNo());
			emergencyContacts.setName(emergencyContactsReq.getName());
			emergencyContacts.setRelation(emergencyContactsReq.getRelation());
			emergencyContacts.setEmployee(employee);
			EmployeeEmergencyContacts savedContacts = emergencyContactsRepo.save(emergencyContacts);
			return savedContacts;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR,
					new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
							"Exception Occured while Saving EmployeeEmergencyContacts Details"));
		}
	}

	@Override
	public List<EmployeeEmergencyContacts> fetchEmergencyContacts(Integer empID) throws Exception {
		try {
			Employee employee = employeeRepo.findById(empID).get();
			if (employee == null|| employee.getActive() == 0 ) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"please check employee ID"));
			}
			List<EmployeeEmergencyContacts> emergencyContacts = emergencyContactsRepo.findByEmployeeEmpID(empID);
			if(CollectionUtils.isEmpty(emergencyContacts)) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"No Records Found"));
			}
			return emergencyContacts;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR,
					new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
							"Exception Occured while Fetching EmployeeEmergencyContacts Details"));
		}
	}

	@Override
	public MessageResponse deleteEmergencyContacts(Integer id) throws Exception {
		try {
			Optional<EmployeeEmergencyContacts> emergencyContacts = emergencyContactsRepo.findById(id);
			if(emergencyContacts.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"No Records Found to delete"));
			}
			emergencyContactsRepo.deleteById(id);
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setMessage("Deleted Successfully");
			return messageResponse;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR,
					new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
							"Exception Occured while Deleting EmployeeEmergencyContacts Details"));
		}
	}

	@Override
	public EmployeeEmergencyContacts updatEmergencyContacts(Integer id,EmergencyContactsReq emergencyContactsReq) throws Exception {
		try {
			Optional<EmployeeEmergencyContacts> emergencyContacts = emergencyContactsRepo.findById(id);
			if(emergencyContacts.isEmpty()) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"No Records Found to delete"));
			}
			EmployeeEmergencyContacts emergencyContacts2 = emergencyContacts.get();
			emergencyContacts2.setContactNo(emergencyContactsReq.getContactNo());
			emergencyContacts2.setName(emergencyContactsReq.getName());
			emergencyContacts2.setRelation(emergencyContactsReq.getRelation());
			EmployeeEmergencyContacts savedContacts = emergencyContactsRepo.save(emergencyContacts2);

			return savedContacts;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR,
					new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
							"Exception Occured while Updating EmployeeEmergencyContacts Details"));
		}
	}

}
