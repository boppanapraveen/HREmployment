package com.employee.service;

import java.util.List;

import com.employee.bean.RecruitmentRequest;
import com.employee.bean.RecruitmentUpdateReq;
import com.employee.model.Recruitment;

public interface RecruitmentService {
	
	public Recruitment addRecruitmentDetails(RecruitmentRequest recruitmentRequest) throws Exception;
	
	public Recruitment fetchRecruitment(Integer recruitmentID) throws Exception;
	
	public Recruitment updateStatusRecruitment(RecruitmentUpdateReq recruitmentUpdateReq) throws Exception;	
	
	public Recruitment updateRecruitment(Integer recruitmentID,RecruitmentRequest recruitmentRequest) throws Exception;
	
	public List<Recruitment> fetchAllRecruitments(Integer hrEmpID) throws Exception;
	
}
