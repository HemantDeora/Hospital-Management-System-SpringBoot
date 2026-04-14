package com.training.Repo;

import com.training.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.firstName = :firstName")
    public List<Patient> findByFirstName(String firstName);

    long countByGender(String gender);
}
