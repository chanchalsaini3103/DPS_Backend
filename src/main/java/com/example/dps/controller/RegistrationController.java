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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
            Parent parent = student.getParent();

            // 🔍 Check for existing Parent
            Optional<Parent> existingParent = parentRepository.findByFatherEmail(parent.getFatherEmail());
            if (existingParent.isPresent()) {
                parent = existingParent.get(); // reuse existing parent
            } else {
                // Duplicate checks
                boolean emailExists = parentRepository.existsByMotherEmail(parent.getMotherEmail());
                boolean phoneExists = parentRepository.existsByFatherPhone(parent.getFatherPhone()) ||
                        parentRepository.existsByMotherPhone(parent.getMotherPhone());

                if (emailExists) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(Collections.singletonMap("error", "Email already registered."));
                }

                if (phoneExists) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(Collections.singletonMap("error", "Phone already registered."));
                }

                // Save new parent if not found
                parent = parentRepository.save(parent);
            }

            // 🔁 Now parent is saved → check for duplicate student under this parent
            if (studentRepository.existsByFirstNameAndLastNameAndDobAndParent(
                    student.getFirstName(), student.getLastName(), student.getDob(), parent)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("error", "Student already registered under this parent."));
            }

            student.setParent(parent); // attach parent
            studentRepository.save(student); // save student

            return ResponseEntity.ok(Collections.singletonMap("message", "Saved successfully"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Registration failed: " + e.getMessage()));
        }
    }

    @GetMapping("/verify-parent")
    public ResponseEntity<?> verifyParent(@RequestParam String email, @RequestParam String phone) {
        boolean exists = parentRepository.existsByFatherEmailAndFatherPhone(email, phone) ||
                parentRepository.existsByMotherEmailAndMotherPhone(email, phone);

        if (exists) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Parent exists"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Email or phone not registered"));
        }
    }

    @GetMapping("/parent/find-by-email-phone")
    public ResponseEntity<Map<String, Object>> getByEmailAndPhone(@RequestParam String email, @RequestParam String phone) {
        Optional<Parent> parentOpt = parentRepository.findByAnyParentEmailAndPhone(email, phone);
        if (parentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Parent parent = parentOpt.get();
        String loggedInAs = "";

        if (email.equals(parent.getFatherEmail()) && phone.equals(parent.getFatherPhone())) {
            loggedInAs = "Father";
        } else if (email.equals(parent.getMotherEmail()) && phone.equals(parent.getMotherPhone())) {
            loggedInAs = "Mother";
        }

        Map<String, Object> response = new HashMap<>();
        response.put("parent", parent);
        response.put("loggedInAs", loggedInAs);

        return ResponseEntity.ok(response);
    }


}
