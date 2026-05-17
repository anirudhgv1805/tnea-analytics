package com.tneaanalytics.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tneaanalytics.backend.model.Cutoff;

public interface CutoffRepository extends JpaRepository<Cutoff, Long> {

}
