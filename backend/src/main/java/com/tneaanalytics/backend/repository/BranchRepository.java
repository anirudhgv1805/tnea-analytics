package com.tneaanalytics.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tneaanalytics.backend.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
