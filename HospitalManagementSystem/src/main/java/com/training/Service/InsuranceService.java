package com.training.Service;

import com.training.Dto.InsuranceDto;
import org.springframework.stereotype.Service;

@Service
public interface InsuranceService {
    InsuranceDto createInsurance(InsuranceDto insuranceDto);

    InsuranceDto getByPatientId(Long patientId);

    InsuranceDto updateInsurance(Long id, InsuranceDto dto);

    void deleteInsurance(Long id);
}
