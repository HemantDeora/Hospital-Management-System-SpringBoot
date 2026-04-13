package com.training.Service;

import com.training.Dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {

    AppointmentDto createAppointment(AppointmentDto dto);

    List<AppointmentDto> getAllAppointments();

    AppointmentDto getAppointmentById(Long id);

    void deleteAppointment(Long id);
}