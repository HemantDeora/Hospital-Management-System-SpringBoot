package com.training.Controller;

import com.training.Dto.Patientdto;
import com.training.Service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patientdto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patientdto> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<Patientdto> savePatient(
            @Valid @RequestBody Patientdto patientdto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientService.savePatient(patientdto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patientdto> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody Patientdto patientdto){

        return ResponseEntity.ok(patientService.updatePatient(id, patientdto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patientdto> patchPatient(
            @PathVariable Long id,
            @RequestBody Patientdto patientdto){

        return ResponseEntity.ok(patientService.updatePatientPartial(id, patientdto));
    }
}