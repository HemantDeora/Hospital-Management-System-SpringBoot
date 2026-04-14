package com.training.Service.Impl;

import com.training.Dto.DashboardDto;
import com.training.Dto.GenderStatsDto;
import com.training.Dto.RecentAppointmentDto;
import com.training.Entity.Appointment;
import com.training.Repo.AppointmentRepo;
import com.training.Repo.DoctorRepo;
import com.training.Repo.PatientRepo;
import com.training.Service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentRepo appointmentRepo;

    @Override
    public DashboardDto getDashboardData() {

        // 🔥 Total Counts
        long totalPatients = patientRepo.count();
        long totalDoctors = doctorRepo.count();
        long totalAppointments = appointmentRepo.count();

        // 🔥 Today Appointments
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(23, 59, 59);

        long todayAppointments =
                appointmentRepo.countByAppointmentDateBetween(start, end);

        // 🔥 Status Counts
        long completedAppointments =
                appointmentRepo.countByStatus("Completed");

        long pendingAppointments =
                appointmentRepo.countByStatus("Scheduled");

        // 🔥 Recent Appointments (latest first)
        Pageable recentPage = PageRequest.of(
                0, 5,
                Sort.by("appointmentDate").descending()
        );

        List<RecentAppointmentDto> recentAppointments =
                appointmentRepo.findAll(recentPage)
                        .getContent()
                        .stream()
                        .map(app -> new RecentAppointmentDto(
                                app.getId(),
                                app.getPatient() != null ? app.getPatient().getFirstName() : "N/A",
                                app.getDoctor() != null ? app.getDoctor().getFirstName() : "N/A",
                                app.getAppointmentDate()
                        ))
                        .toList();

        // 🔥 Upcoming Appointments (future first)
        Pageable upcomingPage = PageRequest.of(
                0, 5,
                Sort.by("appointmentDate").ascending()
        );

        List<RecentAppointmentDto> upcomingAppointments =
                appointmentRepo.findAll(upcomingPage)
                        .getContent()
                        .stream()
                        .map(app -> new RecentAppointmentDto(
                                app.getId(),
                                app.getPatient() != null ? app.getPatient().getFirstName() : "N/A",
                                app.getDoctor() != null ? app.getDoctor().getFirstName() : "N/A",
                                app.getAppointmentDate()
                        ))
                        .toList();

        // 🔥 Gender Stats
        long male = patientRepo.countByGender("Male");
        long female = patientRepo.countByGender("Female");

        GenderStatsDto genderStats = new GenderStatsDto(male, female);

        // 🔥 Final Response
        return new DashboardDto(
                totalPatients,
                totalDoctors,
                totalAppointments,
                todayAppointments,
                completedAppointments,
                pendingAppointments,
                recentAppointments,
                upcomingAppointments,
                genderStats
        );
    }
}