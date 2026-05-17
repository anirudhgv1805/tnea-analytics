package com.tneaanalytics.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tneaanalytics.backend.repository.BranchRepository;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;
    
}
