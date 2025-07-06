package com.example.dps.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enquiry")
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "academic_year", length = 50)
    private String academicYear;

    @Column(name = "school", length = 100)
    private String school;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "relation", length = 20)
    private String relation;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "country_code", length = 10)
    private String countryCode;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Column(name = "grade", length = 50)
    private String grade;

    @Column(name = "query", columnDefinition = "TEXT")
    private String query;

    // Getters and Setters
    // (You can use Lombok @Getter @Setter or manually add them)
}
