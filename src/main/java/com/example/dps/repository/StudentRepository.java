package com.example.dps.repository;

import com.example.dps.model.Parent;
import com.example.dps.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByFirstNameAndLastNameAndDobAndParent(
            String firstName, String lastName, String dob, Parent parent);
}
