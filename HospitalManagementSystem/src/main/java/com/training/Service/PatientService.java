package com.training.Service;

import com.training.Dto.Patientdto;

import java.util.List;

public interface PatientService {
    List<Patientdto> getAllPatients();

    Patientdto getPatientById(Long id);

    Patientdto savePatient(Patientdto patientdto);

    void deletePatient(Long id);

    Patientdto updatePatient(Long id, Patientdto patientdto);

    Patientdto updatePatientPartial(Long id, Patientdto patientdto);

    List<Patientdto> searchPatients(String firstName);
}
