package com.example.dps.controller;


import com.example.dps.model.Parent;
import com.example.dps.model.Student;
import com.example.dps.repository.ParentRepository;
import com.example.dps.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private StudentRepository studentRepository;



    @Autowired
    private ParentRepository parentRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        try {
            // Save the parent first
            Parent savedParent = parentRepository.save(student.getParent());

            // Set the saved parent to the student
            student.setParent(savedParent);

            // Save the student
            studentRepository.save(student);

            return ResponseEntity.ok(Collections.singletonMap("message", "Saved successfully"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Registration failed: " + e.getMessage()));

        }
    }


}
