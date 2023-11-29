package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Recruitment;
import java.util.List;


@Repository
public interface RecruitmentRepo extends JpaRepository<Recruitment, Integer>{
	Recruitment findByCandidateNameAndStatus(String candidateName, String status);
}
