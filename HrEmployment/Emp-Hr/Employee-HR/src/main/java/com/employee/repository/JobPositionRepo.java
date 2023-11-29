package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.JobPosition;
import java.util.List;


@Repository
public interface JobPositionRepo extends JpaRepository<JobPosition, Integer>{
	public JobPosition findByJobTitle(String jobTitle);
}
