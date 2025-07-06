package com.example.dps.repository;

import com.example.dps.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    Optional<Parent> findByFatherEmail(String fatherEmail);
    boolean existsByFatherEmailAndFatherPhone(String email, String phone);
    boolean existsByMotherEmailAndMotherPhone(String email, String phone);
    Optional<Parent> findByFatherEmailAndFatherPhone(String email, String phone);

    @Query("SELECT p FROM Parent p WHERE (p.fatherEmail = :email AND p.fatherPhone = :phone) OR (p.motherEmail = :email AND p.motherPhone = :phone)")
    Optional<Parent> findByAnyParentEmailAndPhone(@Param("email") String email, @Param("phone") String phone);

    boolean existsByFatherEmail(String fatherEmail);
    boolean existsByMotherEmail(String motherEmail);
    boolean existsByFatherPhone(String fatherPhone);
    boolean existsByMotherPhone(String motherPhone);
}