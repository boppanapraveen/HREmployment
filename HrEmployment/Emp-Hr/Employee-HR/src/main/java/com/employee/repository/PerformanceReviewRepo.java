package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.PerformanceReview;

@Repository
public interface PerformanceReviewRepo extends JpaRepository<PerformanceReview, Integer>{
	public List<PerformanceReview> findByEmployeeEmpIDAndEmployeeActive(Integer empID,Integer active);
}
