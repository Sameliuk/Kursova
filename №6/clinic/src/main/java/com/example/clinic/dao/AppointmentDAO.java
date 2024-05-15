package com.example.clinic.dao;

import com.example.clinic.model.Appointment;
import com.example.clinic.model.User;

import java.util.Collection;

public interface AppointmentDAO {

    Appointment findById(Integer id);

    Collection<Appointment> findAll();

    void create(Appointment appointment);

    void update(Appointment appointment);

    void delete(Integer id);

    Collection<Appointment> findByUserId(Integer userId);

}
