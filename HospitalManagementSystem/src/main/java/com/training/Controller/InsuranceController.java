package com.training.Controller;

import com.training.Dto.InsuranceDto;
import com.training.Service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insurance")
public class InsuranceController {
    private final InsuranceService insuranceService;

    @PostMapping
    public ResponseEntity<InsuranceDto> createInsurance(@RequestBody InsuranceDto insuranceDto) {
        return ResponseEntity.ok(insuranceService.createInsurance(insuranceDto));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<InsuranceDto> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(insuranceService.getByPatientId(patientId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceDto> update(@PathVariable Long id,
                                               @RequestBody InsuranceDto dto) {
        return ResponseEntity.ok(insuranceService.updateInsurance(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
        return ResponseEntity.noContent().build();
    }
}
