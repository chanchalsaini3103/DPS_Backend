package com.example.dps.repository;

import com.example.dps.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
}
