package com.example.clinic.services;

import com.example.clinic.dao.AppointmentDAO;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.User;

import java.util.Collection;

public abstract class AppointmentService {

    protected AppointmentDAO appointmentDAO;

    public AppointmentService() {
        this.appointmentDAO = appointmentDAO;
    }

    public abstract Collection<Appointment> getAllAppointments();

    public abstract Appointment getAppointmentById(Integer id);

    public abstract void addAppointment(Appointment appointment);

    public abstract void addAppointment(User user, Appointment appointment);

    public abstract void deleteAppointment(Integer id);

    public abstract Collection<Appointment> getAppointmentsByUserId(Integer userId);
}
