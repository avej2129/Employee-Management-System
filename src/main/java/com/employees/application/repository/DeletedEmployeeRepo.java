package com.employees.application.repository;

import com.employees.application.model.DeletedEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedEmployeeRepo extends JpaRepository<DeletedEmployee,Integer> {
}
