package com.example.dps.controller;

import com.example.dps.model.Enquiry;
import com.example.dps.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EnquiryController {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @PostMapping("/enquiry")
    public ResponseEntity<?> saveEnquiry(@RequestBody Enquiry enquiry) {
        enquiryRepository.save(enquiry);
        return ResponseEntity.ok("Enquiry saved");
    }



}
