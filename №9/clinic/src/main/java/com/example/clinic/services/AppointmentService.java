package com.example.clinic.services;

import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;

import java.time.LocalDateTime;
import java.util.Collection;

public interface AppointmentService {

     Appointment getAppointmentById(Integer id);

     void addAppointment(User user, Doctor doctor, String time);


    void deleteAppointment(User user, Integer appointmentId);

    Collection<Appointment> getAppointmentsByUserId(Integer userId);

    Collection<Appointment> getAllAppointments();
}
