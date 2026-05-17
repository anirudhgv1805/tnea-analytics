package com.tneaanalytics.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tneaanalytics.backend.repository.AllotmentRepository;

@Service
public class AllotmentService {

    @Autowired
    private AllotmentRepository allotmentRepository;
}
