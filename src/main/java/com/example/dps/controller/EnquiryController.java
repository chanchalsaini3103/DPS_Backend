package com.example.dps.controller;

import com.example.dps.model.Enquiry;
import com.example.dps.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @PostMapping
    public Enquiry submitEnquiry(@RequestBody Enquiry enquiry) {
        return enquiryRepository.save(enquiry);
    }
}
