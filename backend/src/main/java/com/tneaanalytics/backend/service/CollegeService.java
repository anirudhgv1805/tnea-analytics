package com.tneaanalytics.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tneaanalytics.backend.repository.CollegeRepository;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

}
