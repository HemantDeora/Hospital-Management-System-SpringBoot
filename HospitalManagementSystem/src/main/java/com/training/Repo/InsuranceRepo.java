package com.training.Repo;

import com.training.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepo extends JpaRepository<Insurance, Long> {

  Optional<Insurance> findByPatientId(Long patientID);
}
