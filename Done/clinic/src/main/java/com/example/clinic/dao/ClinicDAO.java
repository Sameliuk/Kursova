package com.example.clinic.dao;

public interface ClinicDAO {

    UserDAO getUserDAO();

    DoctorDAO getDoctorDAO();

    AppointmentDAO getAppointmentDAO();

    ScheduleDAO getScheduleDAO();

  }
