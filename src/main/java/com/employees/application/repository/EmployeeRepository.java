package com.employees.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employees.application.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e.firstName, e.lastName , e.phoneNo FROM Employee e")
     List<String> getPhoneNumber();

     boolean existsByEmployeeId(int employeeId);
}
