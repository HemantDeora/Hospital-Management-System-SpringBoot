package com.training.Controller;

import com.training.Dto.DoctorDto;
import com.training.Service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(
            @Valid @RequestBody DoctorDto doctorDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorService.saveDoctor(doctorDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorDto doctorDto) {

        return ResponseEntity.ok(doctorService.updateDoctor(id, doctorDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DoctorDto> patchDoctor(
            @PathVariable Long id,
            @RequestBody DoctorDto doctorDto) {

        return ResponseEntity.ok(doctorService.patchDoctor(id, doctorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}