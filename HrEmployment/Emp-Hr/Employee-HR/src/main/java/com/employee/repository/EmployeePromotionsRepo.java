package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.EmployeePromotions;

@Repository
public interface EmployeePromotionsRepo extends JpaRepository<EmployeePromotions, Integer>{
	public List<EmployeePromotions> findByEmployeeEmpIDAndEmployeeActive(Integer empID,Integer active);
}
