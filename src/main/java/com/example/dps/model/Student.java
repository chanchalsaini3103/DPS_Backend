package com.example.dps.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String firstName;
    private String middleName;
    private String lastName;
    private String academicYear;
    private String dob;
    private String grade;
    private String gender;
    private int age;

    private boolean paymentCompleted = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Parent parent;
}
