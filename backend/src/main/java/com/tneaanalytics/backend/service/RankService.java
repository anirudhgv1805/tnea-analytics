package com.tneaanalytics.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tneaanalytics.backend.repository.RankRepository;

@Service
public class RankService {

    @Autowired
    private RankRepository rankRepository;

}
