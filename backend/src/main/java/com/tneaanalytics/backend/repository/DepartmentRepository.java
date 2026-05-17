package com.tneaanalytics.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tneaanalytics.backend.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
