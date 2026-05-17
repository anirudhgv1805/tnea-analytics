package com.tneaanalytics.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tneaanalytics.backend.model.Allotment;

public interface AllotmentRepository extends JpaRepository<Allotment, Long> {

}
