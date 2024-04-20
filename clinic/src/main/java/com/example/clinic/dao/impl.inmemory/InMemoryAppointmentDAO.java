package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.AppointmentDAO;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.User;

import java.util.*;

public class InMemoryAppointmentDAO implements AppointmentDAO {
    private final Map<Integer, Appointment> appointments;
    private int nextId = 1;

    public InMemoryAppointmentDAO(InMemoryDatabase database) {
        this.appointments = new HashMap<>();
    }

    @Override
    public Appointment get(Integer id) {
        return appointments.get(id);
    }

    @Override
    public Collection<Appointment> findAll() {
        return appointments.values();
    }

    @Override
    public void insert(Appointment entity) {
        entity.setAppointmentId(nextId++);
        appointments.put(entity.getAppointmentId(), entity);
    }

    @Override
    public void delete(Integer id) {
        appointments.remove(id);
    }

    @Override
    public Collection<Appointment> findByUserId(Integer userId) {
        List<Appointment> userAppointments = new ArrayList<>();
        for (Appointment appointment : appointments.values()) {
            if (appointment.getUserId().equals(userId)) {
                userAppointments.add(appointment);
            }
        }
        return userAppointments;
    }

    @Override
    public void addAppointment(User user, Appointment appointment) {
        appointment.setUserId(user.getUserId());
        insert(appointment);
    }

    @Override
    public void deleteAppointment(Integer appointmentId) {
        delete(appointmentId);
    }

    @Override
    public Collection<Appointment> findByUserId() {
        // Цей метод, ймовірно, не потрібний, оскільки маємо findByUserId(Integer userId)
        return null;
    }
}
