package com.example.clinic.dao;

import com.example.clinic.model.Appointment;
import com.example.clinic.model.User;

import java.util.Collection;

public interface AppointmentDAO {

    Appointment get(Integer id);

    Collection<Appointment> findAll();

    void insert(Appointment appointment);


    void delete(Integer id);

    Collection<Appointment> findByUserId(Integer userId);

    void addAppointment(User user, Appointment appointment);

    void deleteAppointment(Integer appointmentId);

    Collection<Appointment> findByUserId();
}
