package com.training.Service;

import com.training.Dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> getAllDoctors();

    DoctorDto getDoctorById(Long id);

    DoctorDto saveDoctor(DoctorDto doctorDto);

    DoctorDto updateDoctor(Long id, DoctorDto doctorDto);

    DoctorDto patchDoctor(Long id, DoctorDto doctorDto);

    void deleteDoctor(Long id);
}