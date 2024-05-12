package com.example.clinic.services;

import com.example.clinic.model.Appointment;

import java.util.Collection;

public interface AppointmentService {

     Appointment getAppointmentById(Integer id);

     void addAppointment(Appointment appointment);

    void updateAppointment(Appointment appointment);

    void deleteAppointment(Integer id);

    Collection<Appointment> getAppointmentsByUserId(Integer userId);
}
