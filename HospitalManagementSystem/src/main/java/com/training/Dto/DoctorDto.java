package com.training.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private Long id;

    private String firstName;
    private String lastName;
    private String specialization;

    private String email;
    private String phone;

    private int experience;

    private String availability;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}