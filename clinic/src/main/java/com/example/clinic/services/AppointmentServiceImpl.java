package com.example.clinic.services;

import com.example.clinic.dao.AppointmentDAO;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.User;

import java.util.Collection;

public class AppointmentServiceImpl extends AppointmentService {

    protected AppointmentDAO appointmentDAO;

    public AppointmentServiceImpl(AppointmentDAO appointmentDAO) {
        super();
        this.appointmentDAO = appointmentDAO;
    }

    @Override
    public Collection<Appointment> getAllAppointments() {
        return appointmentDAO.findAll();
    }

    @Override
    public Appointment getAppointmentById(Integer id) {
        return appointmentDAO.get(id);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentDAO.insert(appointment);
    }

    @Override
    public void addAppointment(User user, Appointment appointment) {
        appointmentDAO.addAppointment(user, appointment);
    }

    @Override
    public void deleteAppointment(Integer id) {
        appointmentDAO.delete(id);
    }

    @Override
    public Collection<Appointment> getAppointmentsByUserId(Integer userId) {
        return appointmentDAO.findByUserId(userId);
    }
}
