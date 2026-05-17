package com.tneaanalytics.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tneaanalytics.backend.model.College;

public interface CollegeRepository extends JpaRepository<College, Long> {

}
