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

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "school")
    private String school;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "relation")
    private String relation;

    @Column(name = "email")
    private String email;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "grade")
    private String grade;

    @Column(name = "query")
    private String query;

}
