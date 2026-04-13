package com.training.Repo;

import com.training.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

    public List<Patient> findByFirstName(String firstName);
}
