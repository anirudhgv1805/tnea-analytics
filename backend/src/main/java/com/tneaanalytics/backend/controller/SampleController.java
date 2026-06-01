package com.tneaanalytics.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;

@EnableWebSecurity(debug = true)
@RestController
public class SampleController {

    @GetMapping("/")
    public String sampleResponse() {
        return new String("Hello world!");
    }

}
