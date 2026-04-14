package com.training.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDto {

    private long totalPatients;
    private long totalDoctors;
    private long totalAppointments;

    private long todayAppointments;
    private long completedAppointments;
    private long pendingAppointments;

    private List<RecentAppointmentDto> recentAppointments;
    private List<RecentAppointmentDto> upcomingAppointments;

    private GenderStatsDto genderStats;
}