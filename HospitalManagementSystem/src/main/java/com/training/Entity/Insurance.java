package com.training.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "insurance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String providerName;

    @NotBlank
    @Column(unique = true)
    private String policyNumber;

    @NotNull
    private LocalDate validFrom;

    @NotNull
    private LocalDate validTo;

    @OneToOne(mappedBy = "insurance")
    private Patient patient;
}