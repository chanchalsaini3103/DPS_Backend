package com.example.dps.controller;

import com.example.dps.model.Parent;
import com.example.dps.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private ParentRepository parentRepository;

    @GetMapping("/parents")
    public List<Parent> getAllParentsWithStudents() {
        return parentRepository.findAll();
    }
    @GetMapping("/api/dashboard/parent/{id}")
    public ResponseEntity<Parent> getParentById(@PathVariable Long id) {
        Parent parent = parentRepository.findById(id).orElseThrow(() -> new RuntimeException("Parent not found"));
        return ResponseEntity.ok(parent);
    }
    @GetMapping("/parent/{id}")
    public ResponseEntity<Parent> getParentWithStudents(@PathVariable Long id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found"));
        return ResponseEntity.ok(parent);
    }

}
