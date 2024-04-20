package com.example.clinic.dao;

public interface ClinicDAO {

    UserDAO getUserDao();

    DoctorDAO getDoctorDao();

    AppointmentDAO getAppointmentDao();
}
