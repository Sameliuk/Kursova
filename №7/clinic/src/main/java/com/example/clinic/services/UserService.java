package com.example.clinic.services;

import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;

import java.time.LocalDateTime;
import java.util.Collection;

public interface   UserService {

   User getByLogin(String login);

   User getUserId(Integer id);

   boolean checkPassword(User user, String password);

   void addAppointment(User user, Doctor doctor, String time);

   Collection<User> findAll();

   Collection<Appointment> findAllAppointments(User user);
}

