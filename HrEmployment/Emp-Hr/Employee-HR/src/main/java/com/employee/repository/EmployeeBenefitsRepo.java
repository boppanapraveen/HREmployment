package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.EmployeeBenefits;

public interface EmployeeBenefitsRepo extends JpaRepository<EmployeeBenefits, Integer>{

}
