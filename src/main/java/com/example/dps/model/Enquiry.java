package com.example.dps.model;

import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enquiry")
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String academicYear;
    private String school;
    private String firstName;
    private String lastName;
    private String relation;
    private String email;
    private String countryCode;
    private String contactNumber;

    private String grade;
    private String query;

}
