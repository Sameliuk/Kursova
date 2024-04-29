package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.*;

class InMemoryClinicDAO implements ClinicDAO {

    InMemoryDatabase database;

    UserDAO userDAO;
    DoctorDAO doctorDAO;
    AppointmentDAO appointmentDAO;
    ScheduleDAO scheduleDAO;

    InMemoryClinicDAO(InMemoryDatabase database) {
        this.database = database;

        userDAO = new InMemoryUserDAO(database);
        doctorDAO = new InMemoryDoctorDAO(database);
        appointmentDAO = new InMemoryAppointmentDAO(database);
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

    public DoctorDAO getDoctorDAO() {
        return doctorDAO;
    }

    public AppointmentDAO getAppointmentDAO() {
        return appointmentDAO;
    }

    @Override
    public ScheduleDAO getScheduleDAO() {
        return scheduleDAO;
    }

}
