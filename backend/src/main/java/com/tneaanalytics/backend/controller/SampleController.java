package com.tneaanalytics.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class SampleController {
    
    @GetMapping("/")
    public String sampleResponse() {
        return new String("Hello world!");
    }
    
}
