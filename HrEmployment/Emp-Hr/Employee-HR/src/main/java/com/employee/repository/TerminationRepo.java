package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.EmployeeTerminations;

@Repository
public interface TerminationRepo extends JpaRepository<EmployeeTerminations, Integer>{

}
