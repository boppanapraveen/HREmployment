package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.model.Attendance;


@Repository
public interface AttendenceRepo extends JpaRepository<Attendance, Integer>{

    public  List<Attendance> findByEmployeeEmpIDAndEmployeeActive(Integer empID,Integer active);
    
    public Attendance findByDateAndEmployeeEmpIDAndEmployeeActive(String date,Integer empID,Integer active);

}
