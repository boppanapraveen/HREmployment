package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.EmployeeAppraisals;

@Repository
public interface EmployeeAppraisalsRepo extends JpaRepository<EmployeeAppraisals, Integer> {

}
