package com.example.clinic.services;

import com.example.clinic.model.Appointment;
import com.example.clinic.model.User;

import java.util.Collection;

public interface AppointmentService {

     Appointment getAppointmentById(Integer id);

     void addAppointment(User user, Appointment appointment);

    void updateAppointment(User user, Appointment appointment);

    void deleteAppointment(Integer id);

    Collection<Appointment> getAppointmentsByUserId(Integer userId);
}
