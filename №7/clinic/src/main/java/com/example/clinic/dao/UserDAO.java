package com.example.clinic.dao;

import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;

import java.util.Collection;

public interface UserDAO extends AbstractDAO<User>{
    User getByLogin(String login);

    User getUserId(Integer id);

    Collection<User> findAll();

    Collection<Appointment> findAllAppointments(User user);
}
