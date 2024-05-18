package com.example.clinic.dao;

import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;

import java.time.LocalDateTime;
import java.util.Collection;

public interface AppointmentDAO {

    Appointment findById(Integer id);

    Collection<Appointment> findAll();

    void create(User user, Doctor doctor, String time);


    void delete(User user, Integer appointmentId);

    Collection<Appointment> findByUserId(Integer userId);

}
