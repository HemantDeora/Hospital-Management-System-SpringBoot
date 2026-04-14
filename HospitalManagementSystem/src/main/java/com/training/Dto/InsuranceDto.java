package com.training.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDto {

    private Long id;

    @NotBlank(message = "Provider name is required")
    @Size(min = 2, max = 100, message = "Provider name must be between 2 to 100 characters")
    private String providerName;

    @NotBlank(message = "Policy number is required")
    @Size(min = 5, max = 50, message = "Policy number must be between 5 to 50 characters")
    private String policyNumber;

    @NotNull(message = "Valid from date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validFrom;

    @NotNull(message = "Valid to date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validTo;

    @NotNull(message = "Patient ID is required")
    @Positive(message = "Patient ID must be positive")
    private Long patientId;
}