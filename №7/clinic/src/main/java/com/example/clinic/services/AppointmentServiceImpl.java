package com.example.clinic.services;

import com.example.clinic.dao.AppointmentDAO;
import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;

import java.time.LocalDateTime;
import java.util.Collection;

public class AppointmentServiceImpl implements AppointmentService {

    ClinicDAO clinicDAO;

    public AppointmentServiceImpl(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }

    @Override
    public Appointment getAppointmentById(Integer id) {
        return clinicDAO.getAppointmentDAO().findById(id);
    }

    @Override
    public void addAppointment(User user, Doctor doctor, String  time) {
        clinicDAO.getAppointmentDAO().create(user, doctor, time);
    }


    @Override
    public void deleteAppointment(Integer id) {
        clinicDAO.getAppointmentDAO().delete(id);
    }

    @Override
    public Collection<Appointment> getAppointmentsByUserId(Integer userId) {
        return clinicDAO.getAppointmentDAO().findByUserId(userId);
    }

    @Override
    public Collection<Appointment> getAllAppointments() {
        return clinicDAO.getAppointmentDAO().findAll();
    }
}
