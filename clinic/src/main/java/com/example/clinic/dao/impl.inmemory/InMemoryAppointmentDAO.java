package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.AppointmentDAO;
import com.example.clinic.model.Appointment;

import java.util.*;

class InMemoryAppointmentDAO extends InMemoryAbstractDAO<Appointment> implements AppointmentDAO {

    InMemoryAppointmentDAO(InMemoryDatabase database) {
        super(database.appointments, Appointment::getAppointmentId, Appointment::setAppointmentId, database);
    }
    private TreeMap<Integer, Appointment> appointments = new TreeMap<>();


    @Override
    public Appointment findById(Integer id) {
        return appointments.get(id);
    }

    @Override
    public Collection<Appointment> findAll() {
        return appointments.values();
    }

    @Override
    public void create(Appointment appointment) {
        int id = appointments.isEmpty() ? 1 : appointments.lastKey() + 1;
        appointment.setAppointmentId(id);
        appointments.put(id, appointment);
    }

    @Override
    public void update(Appointment appointment) {
        appointments.put(appointment.getAppointmentId(), appointment);
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

}
