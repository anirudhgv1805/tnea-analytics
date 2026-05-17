package com.tneaanalytics.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tneaanalytics.backend.model.Rank;

public interface RankRepository extends JpaRepository<Rank, Long> {

}
