package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.AppointmentDAO;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;

import java.util.*;

class InMemoryAppointmentDAO extends InMemoryAbstractDAO<Appointment> implements AppointmentDAO {

    InMemoryAppointmentDAO(InMemoryDatabase database) {
        super(database.appointments, Appointment::getAppointmentId, Appointment::setAppointmentId, database);
    }
    private TreeMap<Integer, Appointment> appointments = (TreeMap) database.appointments;


    @Override
    public Appointment findById(Integer id) {
        return database.appointments.get(id);
    }

    @Override
    public Collection<Appointment> findAll() {
        return database.appointments.values();
    }

    @Override
    public void create(User user, Doctor doctor, String time) {
        Appointment appointment = new Appointment(-1, user, doctor, time);
        this.insert(appointment, true);
        user.getAppointment().add(appointment);
    }

    @Override
    public void delete(User user, Integer appointmentId) {
        user.getAppointment().remove(findById(appointmentId));
    }

    @Override
    public Collection<Appointment> findByUserId(Integer userId) {
        return database.users.get(userId).getAppointment();
    }

}
