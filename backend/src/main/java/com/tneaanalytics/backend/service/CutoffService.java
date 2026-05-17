package com.tneaanalytics.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tneaanalytics.backend.repository.CutoffRepository;

@Service
public class CutoffService {

    @Autowired
    private CutoffRepository cutoffRepository;
}
