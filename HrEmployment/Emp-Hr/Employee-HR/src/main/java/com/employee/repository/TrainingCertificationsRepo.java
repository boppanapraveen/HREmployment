package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.TrainingCertification;

@Repository
public interface TrainingCertificationsRepo extends JpaRepository<TrainingCertification, Integer>{

}
