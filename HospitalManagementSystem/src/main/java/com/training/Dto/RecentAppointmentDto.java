package com.training.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentAppointmentDto {

    private Long id;
    private String patientName;
    private String doctorName;
    private LocalDateTime date;
}