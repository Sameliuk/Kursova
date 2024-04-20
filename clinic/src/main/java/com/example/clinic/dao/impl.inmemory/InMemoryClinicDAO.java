package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.*;

class InMemoryClinicDAO implements ClinicDAO {

    InMemoryDatabase database;

    UserDAO userDao;
    DoctorDAO doctorDao;
    AppointmentDAO appointmentDao;

    InMemoryClinicDAO(InMemoryDatabase database) {
        this.database = database;

        userDao = new InMemoryUserDAO(database);
        doctorDao = new InMemoryDoctorDAO(database);
        appointmentDao = new InMemoryAppointmentDAO(database);
    }

    @Override
    public UserDAO getUserDao() {
        return userDao;
    }

    public DoctorDAO getDoctorDao() {
        return doctorDao;
    }

    public AppointmentDAO getAppointmentDao() {
        return appointmentDao;
    }

}
