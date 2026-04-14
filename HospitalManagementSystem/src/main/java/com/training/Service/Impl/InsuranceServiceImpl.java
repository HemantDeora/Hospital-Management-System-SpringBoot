package com.training.Service.Impl;

import com.training.Dto.InsuranceDto;
import com.training.Entity.Insurance;
import com.training.Entity.Patient;
import com.training.Repo.InsuranceRepo;
import com.training.Repo.PatientRepo;
import com.training.Service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepo insuranceRepo;
    private final ModelMapper  modelMapper;
    private final PatientRepo patientRepo;



    @Override
    public InsuranceDto createInsurance(InsuranceDto insuranceDto) {

    Patient patient = patientRepo.findById(insuranceDto.getPatientId()).orElseThrow(() -> new RuntimeException("Patient Not Found"));

    Insurance insurance = modelMapper.map(insuranceDto, Insurance.class);
    insurance.setPatient(patient);
    Insurance saved = insuranceRepo.save(insurance);

        return modelMapper.map(saved, InsuranceDto.class);
    }

    @Override
    public InsuranceDto getByPatientId(Long patientId) {

        Insurance insurance = insuranceRepo.findByPatientId(patientId).orElseThrow(() -> new RuntimeException("Patient Not Found"));

        return modelMapper.map(insurance, InsuranceDto.class);
    }

    @Override
    public InsuranceDto updateInsurance(Long id, InsuranceDto dto) {
        Insurance insurance = insuranceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));

        modelMapper.map(dto, insurance);

        return modelMapper.map(insuranceRepo.save(insurance), InsuranceDto.class);

    }

    @Override
    public void deleteInsurance(Long id) {
            insuranceRepo.deleteById(id);
    }
}
