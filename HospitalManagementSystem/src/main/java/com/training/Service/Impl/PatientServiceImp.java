package com.training.Service.Impl;

import com.training.Dto.Patientdto;
import com.training.Entity.Patient;
import com.training.Exception.ResourceNotFoundException;
import com.training.Repo.PatientRepo;
import com.training.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService {

    private final ModelMapper  modelMapper;
    private  final PatientRepo patientRepo;

    @Override
    public List<Patientdto> getAllPatients(Integer pageNumber, Integer pageSize) {

        Pageable p = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by("firstName").ascending()
        );
        Page<Patient> pagePost = patientRepo.findAll(p);
        List<Patient> patientList = pagePost.getContent();
        return patientList.stream()
                .map(pat -> modelMapper.map(pat, Patientdto.class))
                .toList();
    }

    @Override
    public Patientdto getPatientById(Long id) {
        Patient patient = patientRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
        return modelMapper.map(patient, Patientdto.class);
    }

    @Override
    public Patientdto savePatient(Patientdto patientdto) {
        Patient patient = modelMapper.map(patientdto, Patient.class);
        Patient saved =  patientRepo.save(patient);
        return modelMapper.map(saved, Patientdto.class);
    }

    @Override
    public void deletePatient(Long id) {
       if (!patientRepo.existsById(id)) {
           throw new ResourceNotFoundException("Patient not found");
       }else
           patientRepo.deleteById(id);
    }

    @Override
    public Patientdto updatePatient(Long id, Patientdto patientdto) {
        Patient patient = patientRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
        patient.setFirstName(patientdto.getFirstName());
        patient.setLastName(patientdto.getLastName());
        patient.setDob(patientdto.getDob());
        patient.setAddress(patientdto.getAddress());
        patient.setGender(patientdto.getGender());
        Patient saved  =  patientRepo.save(patient);
        return modelMapper.map(saved, Patientdto.class);
    }

    @Override
    public Patientdto updatePatientPartial(Long id, Patientdto patientdto) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new  ResourceNotFoundException("Patient not found"));

        modelMapper.map(patientdto, patient);

        Patient updated = patientRepo.save(patient);

        return modelMapper.map(updated, Patientdto.class);
    }

    @Override
    public List<Patientdto> searchPatients(String firstName) {
        List<Patient> patients = patientRepo.findByFirstName(firstName);
        return patients.stream()
                .map(p -> modelMapper.map(p, Patientdto.class))
                .toList();

    }


}
