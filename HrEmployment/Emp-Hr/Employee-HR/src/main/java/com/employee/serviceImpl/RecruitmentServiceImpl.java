package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.bean.RecruitmentRequest;
import com.employee.bean.RecruitmentUpdateReq;
import com.employee.exception.EmployeeHRException;
import com.employee.exception.ErrorResponse;
import com.employee.model.Employee;
import com.employee.model.Recruitment;
import com.employee.repository.EmployeeRepo;
import com.employee.repository.RecruitmentRepo;
import com.employee.service.RecruitmentService;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

	@Autowired
	RecruitmentRepo recruitmentRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public Recruitment addRecruitmentDetails(RecruitmentRequest recruitmentRequest) throws Exception {
		// TODO Auto-generated method stub
		try {
			Employee employee = employeeRepo.findById(recruitmentRequest.getEmpID()).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			Recruitment recruitment = new Recruitment();
			recruitment.setApplicationDate(recruitmentRequest.getApplicationDate());
			recruitment.setCandidateName(recruitmentRequest.getCandidateName());
			recruitment.setInterviewDate(recruitmentRequest.getInterviewDate());
			recruitment.setJobTitle(recruitmentRequest.getJobTitle());
			Recruitment savedRecruitment = recruitmentRepo.save(recruitment);
			return savedRecruitment;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Saving Recruitment Details"));
		}
	}

	@Override
	public Recruitment fetchRecruitment(Integer recruitmentID) throws Exception {
		try {
			Recruitment recruitment = recruitmentRepo.findById(recruitmentID).get();
			if (recruitment == null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Recruitment ID is Incorrect Please Check "));
			}
			return recruitment;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Fetching Recruitment Details"));
		}
	}

	@Override
	public Recruitment updateStatusRecruitment(RecruitmentUpdateReq updateReq) throws Exception {
		try {
			Employee employee = employeeRepo.findById(updateReq.getEmpID()).get();
			if (employee == null || !employee.getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			Recruitment recruitment = recruitmentRepo.findById(updateReq.getRecruitmentID()).get();
			if (recruitment == null) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND,
						new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Recruitment ID is Incorrect Please Check "));
			}
			recruitment.setStatus(updateReq.getStatus());
			Recruitment updatedRecruitment = recruitmentRepo.save(recruitment);
			return updatedRecruitment;
		} catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Updating Recruitment Status"));
		}
	}

	@Override
	public Recruitment updateRecruitment(Integer recruitmentID, RecruitmentRequest recruitmentRequest)
			throws Exception {
		try {
			Recruitment recruitment = fetchRecruitment(recruitmentID);
			recruitment.setApplicationDate(recruitmentRequest.getApplicationDate());
			recruitment.setCandidateName(recruitmentRequest.getCandidateName());
			recruitment.setInterviewDate(recruitmentRequest.getInterviewDate());
			recruitment.setJobTitle(recruitmentRequest.getJobTitle());
			Recruitment savedRecruitment = recruitmentRepo.save(recruitment);
			return savedRecruitment;
		}  catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Updating Recruitment Details"));
		}
	}

	@Override
	public List<Recruitment> fetchAllRecruitments(Integer hrEmpID) throws Exception {
		try {
			Optional<Employee> employee = employeeRepo.findById(hrEmpID);
			if (employee.get() == null || !employee.get().getRole().equals("HR")) {
				throw new EmployeeHRException(HttpStatus.NOT_FOUND, new ErrorResponse(HttpStatus.NOT_FOUND.name(),
						"Could not find an employee or you are not HR, please check employee ID"));
			}
			return recruitmentRepo.findAll();

		}  catch (EmployeeHRException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeHRException(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.name(), "Exception Occured while Updating Recruitment Details"));
		}
	}

}
