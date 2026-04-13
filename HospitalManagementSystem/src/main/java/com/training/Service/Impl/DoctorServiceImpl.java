package com.training.Service.Impl;

import com.training.Dto.DoctorDto;
import com.training.Entity.Doctor;
import com.training.Repo.DoctorRepo;
import com.training.Service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepo.findAll()
                .stream()
                .map(d -> modelMapper.map(d, DoctorDto.class))
                .toList();
    }

    @Override
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        return modelMapper.map(doctor, DoctorDto.class);
    }

    @Override
    public DoctorDto saveDoctor(DoctorDto doctorDto) {
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        Doctor saved = doctorRepo.save(doctor);
        return modelMapper.map(saved, DoctorDto.class);
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        modelMapper.map(doctorDto, doctor);

        Doctor updated = doctorRepo.save(doctor);
        return modelMapper.map(updated, DoctorDto.class);
    }

    @Override
    public DoctorDto patchDoctor(Long id, DoctorDto doctorDto) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        modelMapper.map(doctorDto, doctor);

        Doctor updated = doctorRepo.save(doctor);
        return modelMapper.map(updated, DoctorDto.class);
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepo.existsById(id)) {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
        doctorRepo.deleteById(id);
    }

    @Override
    public List<DoctorDto> searchDoctors(String firstName) {
        List<Doctor> doctors = doctorRepo.findByFirstName(firstName);
        return doctors.stream()
                .map(d -> modelMapper.map(d, DoctorDto.class))
                .toList();

    }
}